package it.sella.smb.chat.dto;

import org.springframework.http.HttpHeaders;

public class BaseRequest {
	
	private HttpHeaders headers;

	public HttpHeaders getHeaders() {
		return headers;
	}

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}


}
