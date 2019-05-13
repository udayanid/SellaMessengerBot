package it.sella.smb.fb.service;

import it.sella.smb.fb.dto.UserDetail;
import it.sella.smb.fb.dto.WebhookRequest;

public class FBServiceMock {

	public UserDetail getUserDetail(WebhookRequest requestPayload) {
		final UserDetail userDetail = new UserDetail();
		userDetail.setFirstName("Test");
		userDetail.setLastName("sdfsdf");

		return userDetail;
	}

	public String sendFBMessage(String fbResponsePayload) {
		return "";
	}

}
