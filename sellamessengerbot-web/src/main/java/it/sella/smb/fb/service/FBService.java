package it.sella.smb.fb.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.sella.smb.fb.dto.UserDetail;
import it.sella.smb.fb.dto.WebhookRequest;
import it.sella.smb.utility.HttpRestClient;

@Component
public class FBService {

	private static final Logger LOG = Logger.getLogger(FBService.class);

	@Value("${FB_GRAPH_API_URL_MESSAGES}")
	private String FB_GRAPH_API_URL_MESSAGES;

	@Value("${ACCESS_TOKEN}")
	private String ACCESS_TOKEN;

	@Value("${FB_GRAPH_API_URL_ACCESS}")
	private String FB_GRAPH_API_URL_ACCESS;

	@Autowired
	HttpRestClient httpRestClient;

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
		LOG.info("<<<<<<<<<<<<<<<<<<<getUserDetail::{}>>>>>>>>>>>>"+senderId+" -- "+receipentId+" -- "+url);
		final UserDetail userDetail = httpRestClient.getForObject(url, UserDetail.class);
		LOG.info("<<<<<<<<<<<<<<<<<<<getUserDetail::{}>>>>>>>>>>>>"+userDetail.getFirst_name()+" -- "+userDetail.getLast_name()+" -- "+userDetail.getEventType());

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
		return httpRestClient.postForObject(url, fbResponsePayload, String.class, null);
	}


}
