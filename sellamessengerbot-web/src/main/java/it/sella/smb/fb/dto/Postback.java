package it.sella.smb.fb.dto;

public class Postback {

	private String payload;
	private String title;
	/*
	 * { "object": "page", "entry": [{ "id": "437062153490261", "time":
	 * 1555469630374, "messaging": [{ "sender": { "id": "2068406659891626" },
	 * "recipient": { "id": "437062153490261" }, "timestamp": 1555469630015,
	 * "message": { "mid":
	 * "rponMsA3EvnoJ5tpgkUNM-JuxLiYm92OU63KEmX1ZCCL6isF9RuZdEeECvC8Y704O3Idzc3aM4GsboWwOZF0iw",
	 * "seq": 20743, "text": "hi" } }] }] }
	 */

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Postback [payload=");
		builder.append(payload);
		builder.append(", title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}

}