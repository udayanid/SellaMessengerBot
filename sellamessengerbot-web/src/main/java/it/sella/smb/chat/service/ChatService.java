package it.sella.smb.chat.service;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.sella.smb.chat.dto.Eventdata;
import it.sella.smb.chat.dto.IMSession;
import it.sella.smb.chat.dto.MessagePayload;
import it.sella.smb.chat.dto.NewChatInfo;
import it.sella.smb.chat.dto.PollResponse;
import it.sella.smb.chat.dto.Result;
import it.sella.smb.fb.dto.UserDetail;
import it.sella.smb.fb.service.FBService;
import it.sella.smb.utility.HttpRestClient;


@Component
public class ChatService {
	private static final Logger logger = Logger.getLogger(ChatService.class);

	@Value("${IM_LOGIN_URL}")
	private String IM_LOGIN_URL;
	@Value("${CHAT_URL}")
	private String CHAT_URL;
	@Value("${POLL_URL}")
	private String POLL_URL;

	@Autowired
	HttpRestClient httpRestClient;

	@Autowired
	FBService fbService;

	/**
	 * method to do im login
	 *
	 * @param userDetail
	 * @return
	 */
	private ResponseEntity<String> doIMLogin(final UserDetail userDetail) {
		final String mailId = userDetail.getFirst_name().concat("_").concat(userDetail.getLast_name()).concat("@facebook.it");
		final String url = String.format(IM_LOGIN_URL, userDetail.getFirst_name(), userDetail.getLast_name(), mailId);
		logger.info("<<<<<<<doIMLogin::: {} >>>>>>>>>>>>>"+ url);
		final ResponseEntity<String> imLoginResponseEntity = httpRestClient.getForEntity(url, String.class);
		return imLoginResponseEntity;
	}

	/**
	 * Method to get new IM chat session
	 *
	 * @param userDetail
	 * @param senderId
	 * @param recepientId
	 * @return
	 */
	public IMSession getNewBotSession(final UserDetail userDetail, final String senderId,
			final String recepientId) {
		final ResponseEntity<String> imLoginResponseEntity = doIMLogin(userDetail);
		IMSession imSession = null;
		logger.info("<<<<<<<<<<<<<<<<<<Login status code:::{}>>>>>>>>>>>"+ imLoginResponseEntity.getStatusCode());
		if (imLoginResponseEntity.getStatusCode() != HttpStatus.FOUND) {
			logger.info("<<<<<<<<<<Login KO>>>>>>>>>");
		} else {
			logger.info("<<<<<<<<<<Login OK>>>>>>>>>");
			imSession = new IMSession();
			imSession.setFbReceipientId(recepientId);
			imSession.setFbSenderId(senderId);
			final HttpHeaders headers = imLoginResponseEntity.getHeaders();
			final String cookieInfo = headers.getFirst("Set-Cookie");

			final HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			header.add("Cookie", cookieInfo);
			imSession.setHeaders(header);

			imSession.setImChatId(getNewChatId(imSession));
			imSession.setCookieInfo(cookieInfo);
			imSession.setLastRequestDate(LocalDateTime.now());
		}
		return imSession;
	}

	/**
	 * Method to get New IM chat id as String
	 *
	 * @param cookieInfo
	 * @return
	 */
	public String getNewChatId(IMSession imSession) {
		final String newChatRequetPayload = "{\"action\":\"newchat\",\"sourceIntentCode\":\"\"}";

		final NewChatInfo newChatInfo = httpRestClient.postForObject(CHAT_URL, newChatRequetPayload, NewChatInfo.class, imSession.getHeaders());
		logger.info("<<<<<<<<getNewChatId:::{}>>>>>>>"+ newChatInfo.getChatid());
		return newChatInfo.getChatid();
	}

	/**
	 * Method to get IM Polling Response
	 *
	 * @param recipientId
	 * @param chatId
	 * @param cookieInfo
	 * @param totalPolls
	 */
	public void getPollResponse(final IMSession imSession, int totalPolls) {
		final String pollPayload = String.format("{\"chatid\":\"%s\"}", imSession.getImChatId());
		logger.info("<<<<<<<<<polling Request payload::{}>>>>>>>>>>>>>>>"+ pollPayload);
		//		final RestTemplate restTemplate = new RestTemplate();

		//		final HttpEntity<String> pollEntity = new HttpEntity<>(pollPayload, imSession.getHeaders());
		for (int i = 1; i <= totalPolls; i++) {
			//			final ResponseEntity<PollResponse> getPollResponseEntity = restTemplate.postForEntity(POLL_URL, pollEntity,	PollResponse.class);
			//			logger.info("<<<<<<<<<<<getPollResponseEntity status:::{}>>>>>>>>>>>>>>>>"+	getPollResponseEntity.getStatusCode());
			final PollResponse pollResponse = httpRestClient.postForObject(POLL_URL, pollPayload, PollResponse.class, imSession.getHeaders());//getPollResponseEntity.getBody();
			logger.info("<<<<<<<<<<<<<<<<<<<<PollResponse:::{}>>>>>>>>>>>>>>>>>>>>>"+ pollResponse);
			//		logger.info("<<<<<<<<<<<<<poll No.{} => Result Collection Size:::{}>>>>>>>>>>>"+ i+	pollResponse.getResults().size());
			logger.info("<<<<<<<<<<<<PollResponsePayload Status :::{}>>>>>>>>>>>"+ pollResponse.getStatus());
			for (final Result result : pollResponse.getResults()) {
				logger.info("<<<<<<<<<<<<Each Result  :::{}>>>>>>>>>>>>>"+ result);
				String answer = result.getAnswer();
				String message = result.getMessage();
				if ((answer != null) && !answer.isEmpty()) {
					answer = answer.replaceAll("\\\"", "\\\\\"");

					/*
					 * final Messaging answerMessaging = new Messaging();
					 * answerMessaging.setRecipient(new Recipient(imSession.getFbSenderId())); final
					 * Message answerMessage = new Message(); answerMessage.setText(answer);
					 * answerMessaging.setMessage(answerMessage);
					 */


					String imResponsePayload = String.format(
							"{ \"recipient\": { \"id\": \"%s\" }, \"message\": { \"text\": \"%s\" } }",
							imSession.getFbSenderId(), answer);
					logger.info("<<<<<<<<<When answer is not null, then ImResponsePayload::::{}>>>>>>>>>>"+
							imResponsePayload);
					String fbAcknowledgement = fbService.sendFBMessage(imResponsePayload);
					logger.info("***************poll Answer Acknowledgement of fb:::{}****************"+
							fbAcknowledgement);
					if ((result.getLink() != null) && !result.getLink().isEmpty()) {

						imResponsePayload = String.format(
								"{ \"recipient\":{ \"id\":\"%s\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"open_graph\", \"elements\":[ { \"url\":\"%s\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it\", \"title\":\"View More\" } ] } ] } } } }",
								imSession.getFbSenderId(), result.getLink());
						logger.info("<<<<<<<<<When link is not null, then ImResponsePayload::::{}>>>>>>>>>>"+
								imResponsePayload);
						fbAcknowledgement = fbService.sendFBMessage(imResponsePayload);
						logger.info("++++++++++++++++++poll link Acknowledgement of fb:::{}++++++++++++++++++"+
								fbAcknowledgement);
					}
				}

				if ((message != null) && !message.isEmpty()) {
					message = message.replaceAll("\\\"", "\\\\\"");
					final String imResponsePayload = String.format(
							"{ \"recipient\": { \"id\": \"%s\" }, \"message\": { \"text\": \"%s\" } }",
							imSession.getFbSenderId(), message);
					logger.info("<<<<<<<<<When message is not null, then ImResponsePayload::::{}>>>>>>>>>>"+
							imResponsePayload);
					final String fbAcknowledgement = fbService.sendFBMessage(imResponsePayload);
					logger.info("*********************poll Message Acknowledgement of fb:::{}***************"+
							fbAcknowledgement);
				}
			}
			try {
				Thread.sleep(new Long(2000));
			} catch (final InterruptedException e) {
				logger.debug("<<<<<<<<<<<<<<<<Exception caught here:{}>>>>>>>>>>>>>>>>>>>"+ e.getMessage());
			}
		}
	}

	/**
	 * Method to send fb message to IM
	 *
	 * @param chatId
	 * @param fbMessage
	 * @param cookieInfo
	 * @return
	 */
	public ResponseEntity<NewChatInfo> sendImMessage(final IMSession imSession, final String fbMessage) {
		final MessagePayload messagepayload = new MessagePayload();
		messagepayload.setChatid(imSession.getImChatId());
		final Eventdata eventdata = new Eventdata();
		eventdata.setValue(fbMessage);
		messagepayload.addEventData(eventdata);
		logger.info("<<<<<<<<<<<<<<<<IM requestMessagePayload::{}>>>>>>>>>>>"+ messagepayload);

		final HttpEntity<MessagePayload> messageEntity = new HttpEntity<>(messagepayload, imSession.getHeaders());
		final RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<NewChatInfo> sendImMessageResponseEntity = restTemplate.postForEntity(CHAT_URL,
				messageEntity, NewChatInfo.class);
		logger.info("<<<<<<<<<<<sendImMessageResponseEntity:::{}>>>>>>>>>>>>>>>>"+
				sendImMessageResponseEntity.getStatusCode());
		return sendImMessageResponseEntity;
		/*
		 *
		 * Requesting to bot for new message { "action": "chatevent", "chatid":
		 * "d2bj9hvl1dp0fe392hls908e51", "idevent": "chatmessage", "sourceIntentCode":
		 * "", "eventdata": [{ "name": "message", "value": "hello" }] }
		 *
		 * incase of chatid not found in bot { "status": "EXCEPTION", "errors": [{
		 * "messageCode": "IM_CHAT_ID_NOT_FOUND", "messageParams": [],
		 * "messageFEFields": "*" }], "requests": null, "codes": null, "contextChange":
		 * null, "concepts": null, "favorites": null, "chatid": null, "chaturl": null,
		 * "result": null, "cause": null, "licenses": null, "transcript": null,
		 * "overTime": null, "errorMessageCode": "IM_CHAT_ID_NOT_FOUND" }
		 *
		 */
	}

}