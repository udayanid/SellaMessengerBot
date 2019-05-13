package it.sella.smb.chat.dto;

import java.util.ArrayList;
import java.util.List;

public class MessagePayload {

	private String action="chatevent";
	private String chatid;
	private String idevent="chatmessage";
	private String sourceIntentCode;
	private List<Eventdata> eventdata = null;

	public void addEventData(Eventdata eventdatum) {
		if (this.eventdata == null) {
			this.eventdata = new ArrayList<Eventdata>();

		}
		this.eventdata.add(eventdatum);
	}

	public String getAction() {
		return action;
	}

	public String getChatid() {
		return chatid;
	}

	public List<Eventdata> getEventdata() {
		return eventdata;
	}

	public String getIdevent() {
		return idevent;
	}

	public String getSourceIntentCode() {
		return sourceIntentCode;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setChatid(String chatid) {
		this.chatid = chatid;
	}

	public void setEventdata(List<Eventdata> eventdata) {
		this.eventdata = eventdata;
	}

	public void setIdevent(String idevent) {
		this.idevent = idevent;
	}

	public void setSourceIntentCode(String sourceIntentCode) {
		this.sourceIntentCode = sourceIntentCode;
	}

	@Override
	public String toString() {
		return "MessagePayload [action=" + action + ", chatid=" + chatid + ", idevent=" + idevent
				+ ", sourceIntentCode=" + sourceIntentCode + ", eventdata=" + eventdata + "]";
	}


}