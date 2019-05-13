package it.sella.smb.fb.dto;

import java.util.List;

public class WebhookRequest {

	private String object;
	private List<Entry> entry = null;

	/*
	 * { "object": "page", "entry": [{ "id": "437062153490261", "time":
	 * 1555469630374, "messaging": [{ "sender": { "id": "2068406659891626" },
	 * "recipient": { "id": "437062153490261" }, "timestamp": 1555469630015,
	 * "message": { "mid":
	 * "rponMsA3EvnoJ5tpgkUNM-JuxLiYm92OU63KEmX1ZCCL6isF9RuZdEeECvC8Y704O3Idzc3aM4GsboWwOZF0iw",
	 * "seq": 20743, "text": "hi" } }] }] }
	 */
	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public List<Entry> getEntry() {
		return entry;
	}

	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestPayload [object=");
		builder.append(object);
		builder.append(", entry=");
		builder.append(entry);
		builder.append("]");
		return builder.toString();
	}

}