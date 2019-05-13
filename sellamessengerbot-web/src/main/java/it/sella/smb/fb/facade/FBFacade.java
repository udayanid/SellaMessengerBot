package it.sella.smb.fb.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sella.smb.fb.dto.UserDetail;
import it.sella.smb.fb.dto.WebhookRequest;
import it.sella.smb.fb.service.FBService;

@Component
public class FBFacade {
	
	@Autowired
	FBService fbService;
	
	public String sendFBMessage(String fbResponsePayload) {
		return fbService.sendFBMessage(fbResponsePayload);
	}
	
	public String sendFBActionMessage(String senderAction, String senderId) {
		return fbService.sendFBActionMessage(senderAction, senderId);
	}
	
	public UserDetail getUserDetail(WebhookRequest requestPayload) {
		return fbService.getUserDetail(requestPayload);
	}
	
	

}
