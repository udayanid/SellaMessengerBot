package it.sella.smb.fb.dto;

import java.util.HashMap;
import java.util.Map;

public class UserDetail {

	private String firstName;
	private String lastName;
	private String profilePic;
	private String id;
	private String senderId;
	private String receipentId;
	private String eventType;
	
//	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
     
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
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
		return "UserDetail [firstName=" + firstName + ", lastName=" + lastName + ", profilePic=" + profilePic + ", id="
				+ id + ", additionalProperties=" + additionalProperties + "]";
	}

}