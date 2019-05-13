package it.sella.smb.fb.dto;

public class Messaging {

	private Sender sender;
	private Recipient recipient;
	private Message message;
	private Postback postback;

	public Messaging() {

	}

	public Messaging(String url) {
		this.message = new Message(url);
	}

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


	public Message getMessage() {
		return message;
	}

	public Postback getPostback() {
		return postback;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public Sender getSender() {
		return sender;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public void setPostback(Postback postback) {
		this.postback = postback;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Messaging [sender=");
		builder.append(sender);
		builder.append(", recipient=");
		builder.append(recipient);
		builder.append(", message=");
		builder.append(message);
		builder.append(", postback=");
		builder.append(postback);
		builder.append("]");
		return builder.toString();
	}

}