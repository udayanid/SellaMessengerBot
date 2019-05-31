package it.sella.smb.chat.facade;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sella.smb.chat.dto.IMSession;
import it.sella.smb.chat.dto.NewChatInfo;
import it.sella.smb.chat.service.ChatService;
import it.sella.smb.fb.dto.Entry;
import it.sella.smb.fb.dto.Messaging;
import it.sella.smb.fb.dto.UserDetail;
import it.sella.smb.fb.dto.WebhookRequest;
import it.sella.smb.fb.facade.FBFacade;

@Component
public class ChatFacade {

	private static Map<String, IMSession> chatSessionMap = new HashMap<String, IMSession>();
	private static final Logger LOG = Logger.getLogger(ChatFacade.class);

	@Autowired
	ChatService chatService;

	@Autowired
	private FBFacade fbFacade;

	/*
	 * public String message(MessageRequest messageRequest) {
	 * chatService.initChatStep1(messageRequest);
	 * chatService.initChatStep2(messageRequest);
	 * chatService.sendMessage(messageRequest); return
	 * chatService.poll(messageRequest); }
	 */

	/**Method to get chat session
	 * @param recipientId:String
	 * @param senderId :String
	 * @param userDetail :UserDetail
	 * @return
	 */
	private IMSession getUserSession(final String recipientId, final String senderId, final UserDetail userDetail) {
		IMSession imSession = null;
		if (chatSessionMap.containsKey(recipientId)) {
			LOG.info("<<<<<<<<<<<<<<<ImSession already available in session>>>>>>>>>>>>>>>>>");
			imSession = chatSessionMap.get(recipientId);
			LOG.info("<<<<<<<<<<<<<<<Existing Session {}>>>>>>>>>>>>>>>>>"+imSession);
			if (LocalDateTime.now().isAfter(imSession.getLastRequestDate().plusMinutes(25))) {
				LOG.info("<<<<<<<<<<<<<<<imSession.getLastRequestDate {}>>>>>>>>>>>>>>>>>"+imSession.getLastRequestDate());
				imSession = chatService.getNewBotSession(userDetail, senderId, recipientId);
			}else if(LocalDateTime.now().isAfter(imSession.getLastRequestDate().plusMinutes(2))) {
				//	imSession.setImChatId(chatService.getNewChatId(imSession));
				//	imSession.setLastRequestDate(LocalDateTime.now());
				imSession = chatService.getNewBotSession(userDetail, senderId, recipientId);
			}
		} else {
			imSession = chatService.getNewBotSession(userDetail, senderId, recipientId);
		}
		LOG.info("chatSessionMap -->"+chatSessionMap.get(recipientId)+" -- "+recipientId+" -- "+chatSessionMap.size());
		chatSessionMap.put(recipientId, imSession);
		return imSession;
	}


	/**Method to process Im Request/Response through messenger Interface
	 * @param imSession:IMSession
	 * @param fbMessage:String
	 */
	private void imProcess(final IMSession imSession, final String fbMessage) {
		NewChatInfo chatResponse = null;
		if((fbMessage!=null) && !fbMessage.isEmpty()) {
			chatResponse =chatService.sendImMessage(imSession, fbMessage).getBody();
			imSession.setLastRequestDate(LocalDateTime.now());
		}
		LOG.info("<<<<<<<<<ChatResponse:::{}>>>>>>>>>>>>>>>"+ chatResponse);
		if((chatResponse!=null) && chatResponse.getStatus().equals("EXCEPTION")) {
			LOG.info("<<<<<<<<<ChatResponse Status:::{}>>>>>>>>>>>>>>>"+ chatResponse.getStatus());
			imSession.setImChatId(chatService.getNewChatId(imSession));
			//			imSession.setLastRequestDate(LocalDateTime.now());
			imProcess(imSession, fbMessage);
		}
		chatService.getPollResponse(imSession, 1);
	}

	public void message(WebhookRequest requestPayload) {
		// Iterating each facebook message entry  and sending it to the bot server
		final UserDetail userDetail = fbFacade.getUserDetail(requestPayload);
		LOG.info("<<<<<<<<<<senderId>>>>{}-------RecipientId>>>{}>>>>>>>>>>>>>>>"+ userDetail.getSenderId()+ userDetail.getReceipentId()+userDetail.getEventType()+ userDetail);
		int total_msg = 0;
		for (final Entry entry : requestPayload.getEntry()) {
			total_msg++;
			LOG.info("<<<<<<<<<<<<Total facebook message entry ::{}>>>>>>>>>>>>>>"+ total_msg);

			for (final Messaging messaging : entry.getMessaging()) {
				String fbMessage = null;
				if((messaging.getPostback()!=null) || (messaging.getMessage()!=null)) {
					fbMessage = userDetail.getEventType().equals("PostbackEvent") ? messaging.getPostback().getPayload() : messaging.getMessage().getText();
				}
				LOG.info("<<<<<<<<<<<<TextMessage::{},EventyType:::{}>>>>>>>>>>>>>>"+ fbMessage);
				String senderActionAcknowledge = fbFacade.sendFBActionMessage("mark_seen", userDetail.getSenderId());
				final IMSession imSession=getUserSession(userDetail.getReceipentId(), userDetail.getSenderId(), userDetail);
				LOG.info("<<<<<<<<<<<<<imSession::{}>>>>>>>>>>>>"+imSession);
				imProcess(imSession, fbMessage);
				senderActionAcknowledge = fbFacade.sendFBActionMessage("typing_off", userDetail.getSenderId());
				LOG.info("senderActionAcknowledge>>>>{}>>>>>>>>>>>"+ senderActionAcknowledge);
			}
		}
	}

}
