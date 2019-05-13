
package it.sella.smb.fb.dto;

import java.util.List;

public class Entry {

	private String id;
	private Long time;
	private List<Messaging> messaging = null;

	/*
	  {
	"object": "page",
	"entry": [{
		"id": "437062153490261",
		"time": 1555469630374,
		"messaging": [{
			"sender": {
				"id": "2068406659891626"
			},
			"recipient": {
				"id": "437062153490261"
			},
			"timestamp": 1555469630015,
			"message": {
				"mid": "rponMsA3EvnoJ5tpgkUNM-JuxLiYm92OU63KEmX1ZCCL6isF9RuZdEeECvC8Y704O3Idzc3aM4GsboWwOZF0iw",
				"seq": 20743,
				"text": "hi"
			}
		}]
	}]
   }
	 */
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Entry [id=");
		builder.append(id);
		builder.append(", time=");
		builder.append(time);
		builder.append(", messaging=");
		builder.append(messaging);
		builder.append("]");
		return builder.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public List<Messaging> getMessaging() {
		return messaging;
	}

	public void setMessaging(List<Messaging> messaging) {
		this.messaging = messaging;
	}

}