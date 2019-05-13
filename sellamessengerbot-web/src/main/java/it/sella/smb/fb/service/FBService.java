package it.sella.smb.fb.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.sella.smb.fb.dto.UserDetail;
import it.sella.smb.fb.dto.WebhookRequest;

@Component
public class FBService {

	private static final Log LOG = LogFactory.getLog(FBService.class);

	@Value("${FB_GRAPH_API_URL_MESSAGES}")
	private String FB_GRAPH_API_URL_MESSAGES;

	@Value("${ACCESS_TOKEN}")
	private String ACCESS_TOKEN;

	@Value("${FB_GRAPH_API_URL_ACCESS}")
	private String FB_GRAPH_API_URL_ACCESS;

	/**Method to get event type as String
	 * @param requestPayload : String
	 * @return eventType : String
	 */
	private String getEventType(WebhookRequest requestPayload) {
		String eventType = "TextEvent";
		if (requestPayload.getEntry().get(0).getMessaging().get(0).getPostback() != null) {
			eventType = "PostbackEvent";
		}
		return eventType;
	}

	/**Method to get receipientId from the fb request payload
	 * @param requestPayload
	 * @return
	 */
	private String getReceipientId(final WebhookRequest requestPayload) {
		return requestPayload.getEntry().get(0).getMessaging().get(0).getRecipient().getId();
	}

	/**Method to get SenderAction ResonsePayload
	 * @param senderAction : String
	 * @param senderId : String
	 * @return String
	 */
	private String getSenderActionResonsePayload(final String senderAction, final String senderId) {
		return String.format("{ \"recipient\":{ \"id\":\"%s\" }, \"sender_action\":\"%s\" }", senderId, senderAction);
	}

	/**Method to get senderId from the fb request payload
	 * @param requestPayload
	 * @return
	 */
	private String getSenderId(final WebhookRequest requestPayload) {
		return requestPayload.getEntry().get(0).getMessaging().get(0).getSender().getId();
	}

	/**method to get user detail : UserDetail
	 * @param senderId:String
	 * @return
	 */
	public UserDetail getUserDetail(WebhookRequest requestPayload) {
		final String senderId = getSenderId(requestPayload);
		final String receipentId = getReceipientId(requestPayload);
		final String url = String.format(FB_GRAPH_API_URL_ACCESS, senderId + "?fields=first_name,last_name,profile_pic&", ACCESS_TOKEN);
		final RestTemplate restTemplate = new RestTemplate();
		final UserDetail userDetail = restTemplate.getForObject(url, UserDetail.class);
		userDetail.setSenderId(senderId);
		userDetail.setReceipentId(receipentId);
		userDetail.setEventType(getEventType(requestPayload));
		return userDetail;
	}

	public String sendFBActionMessage(String senderAction,String senderId) {
		return sendFBMessage(getSenderActionResonsePayload(senderAction,senderId));
	}

	/**Method to send fb message to messenger
	 * @param fbResponsePayload :String
	 * @return
	 */
	public String sendFBMessage(String fbResponsePayload) {
		LOG.info("<<<<<<<<<<<<<<<<<<<ResponsePayload::{}>>>>>>>>>>>>"+fbResponsePayload);
		final String url = String.format(FB_GRAPH_API_URL_MESSAGES, ACCESS_TOKEN);
		final RestTemplate restTemplate = new RestTemplate();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		final HttpEntity<String> entity = new HttpEntity<String>(fbResponsePayload, headers);
		return restTemplate.postForObject(url, entity, String.class);
	}


}
