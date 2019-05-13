
package it.sella.smb.chat.dto;

import java.util.List;

public class NewChatInfo {

	private String status;
	private List<Object> errors = null;
	private Object requests;
	private Object codes;
	private Object contextChange;
	private Object concepts;
	private Object favorites;
	private String chatid;
	private String chaturl;
	private String result;
	private Object cause;
	private Object licenses;
	private Object transcript;
	private String overTime;
	private Object errorMessageCode;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Object> getErrors() {
		return errors;
	}

	public void setErrors(List<Object> errors) {
		this.errors = errors;
	}

	public Object getRequests() {
		return requests;
	}

	public void setRequests(Object requests) {
		this.requests = requests;
	}

	public Object getCodes() {
		return codes;
	}

	public void setCodes(Object codes) {
		this.codes = codes;
	}

	public Object getContextChange() {
		return contextChange;
	}

	public void setContextChange(Object contextChange) {
		this.contextChange = contextChange;
	}

	public Object getConcepts() {
		return concepts;
	}

	public void setConcepts(Object concepts) {
		this.concepts = concepts;
	}

	public Object getFavorites() {
		return favorites;
	}

	public void setFavorites(Object favorites) {
		this.favorites = favorites;
	}

	public String getChatid() {
		return chatid;
	}

	public void setChatid(String chatid) {
		this.chatid = chatid;
	}

	public String getChaturl() {
		return chaturl;
	}

	public void setChaturl(String chaturl) {
		this.chaturl = chaturl;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getCause() {
		return cause;
	}

	public void setCause(Object cause) {
		this.cause = cause;
	}

	public Object getLicenses() {
		return licenses;
	}

	public void setLicenses(Object licenses) {
		this.licenses = licenses;
	}

	public Object getTranscript() {
		return transcript;
	}

	public void setTranscript(Object transcript) {
		this.transcript = transcript;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public Object getErrorMessageCode() {
		return errorMessageCode;
	}

	public void setErrorMessageCode(Object errorMessageCode) {
		this.errorMessageCode = errorMessageCode;
	}
	

}