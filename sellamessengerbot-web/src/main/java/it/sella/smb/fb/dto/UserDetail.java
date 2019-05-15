package it.sella.smb.fb.dto;

import java.util.HashMap;
import java.util.Map;

public class UserDetail {


	private String first_name;
	private String last_name;
	private String profile_pic;
	private String id;
	private String senderId;
	private String receipentId;
	private String eventType;

	//	@JsonIgnore
	private final Map<String, Object> additionalProperties = new HashMap<String, Object>();


	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceipentId() {
		return receipentId;
	}

	public void setReceipentId(String receipentId) {
		this.receipentId = receipentId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@Override
	public String toString() {
		return "UserDetail [firstName=" + first_name + ", lastName=" + last_name + ", profilePic=" + profile_pic + ", id="
				+ id + ", additionalProperties=" + additionalProperties + "]";
	}

}