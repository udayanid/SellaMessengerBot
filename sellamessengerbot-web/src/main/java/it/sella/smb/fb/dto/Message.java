package it.sella.smb.fb.dto;

public class Message {
	private String mid;
	private Integer seq;
	private String text;
	private Attachment attachment;

	public Message() {

	}

	public Message(String url) {
		this.attachment = new Attachment(url);
	}

	public Attachment getAttachment() {
		return attachment;
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
	public String getMid() {
		return mid;
	}

	public Integer getSeq() {
		return seq;
	}

	public String getText() {
		return text;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Message [mid=");
		builder.append(mid);
		builder.append(", seq=");
		builder.append(seq);
		builder.append(", text=");
		builder.append(text);
		builder.append("]");
		return builder.toString();
	}


}