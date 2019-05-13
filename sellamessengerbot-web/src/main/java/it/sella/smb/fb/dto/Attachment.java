package it.sella.smb.fb.dto;

public class Attachment {

	private final String type="template";
	private Payload payload;

	public Attachment() {

	}

	public Attachment(String url) {
		this.payload = new Payload(url);
	}


	public Payload getPayload() {
		return payload;
	}
	public String getType() {
		return type;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}



}
