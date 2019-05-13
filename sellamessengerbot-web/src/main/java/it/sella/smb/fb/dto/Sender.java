package it.sella.smb.fb.dto;

public class Sender {

	private String id;
	/*
	 * { "object": "page", "entry": [{ "id": "437062153490261", "time":
	 * 1555469630374, "messaging": [{ "sender": { "id": "2068406659891626" },
	 * "recipient": { "id": "437062153490261" }, "timestamp": 1555469630015,
	 * "message": { "mid":
	 * "rponMsA3EvnoJ5tpgkUNM-JuxLiYm92OU63KEmX1ZCCL6isF9RuZdEeECvC8Y704O3Idzc3aM4GsboWwOZF0iw",
	 * "seq": 20743, "text": "hi" } }] }] }
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sender [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

}
