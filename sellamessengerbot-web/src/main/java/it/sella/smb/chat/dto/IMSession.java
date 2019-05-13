package it.sella.smb.chat.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;

public class IMSession {

	private String fbSenderId;
	private String fbReceipientId;
	private String imChatId;
	private String cookieInfo;
	private LocalDateTime lastRequestDate;
	private HttpHeaders headers;

	public String getCookieInfo() {
		return cookieInfo;
	}

	public String getFbReceipientId() {
		return fbReceipientId;
	}

	public String getFbSenderId() {
		return fbSenderId;
	}

	public HttpHeaders getHeaders() {
		return headers;
	}

	public String getImChatId() {
		return imChatId;
	}

	public LocalDateTime getLastRequestDate() {
		return lastRequestDate;
	}

	public void setCookieInfo(String cookieInfo) {
		this.cookieInfo = cookieInfo;
	}

	public void setFbReceipientId(String fbReceipientId) {
		this.fbReceipientId = fbReceipientId;
	}

	public void setFbSenderId(String fbSenderId) {
		this.fbSenderId = fbSenderId;
	}

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

	public void setImChatId(String imChatId) {
		this.imChatId = imChatId;
	}

	public void setLastRequestDate(LocalDateTime lastRequestDate) {
		this.lastRequestDate = lastRequestDate;
	}

	@Override
	public String toString() {
		return "IMSession [fbSenderId=" + fbSenderId + ", fbReceipientId=" + fbReceipientId + ", imChatId=" + imChatId
				+ ", cookieInfo=" + cookieInfo + ", lastRequestDate=" + lastRequestDate + "]";
	}

}