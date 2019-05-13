package it.sella.smb.chat.dto;


public class MessageRequest extends BaseRequest {
	
	String message;
	NewChatInfo newChatInfo;
	String nome="nome1";
	String cognome="cognome";
	String email="test3@sella.it";
	String channel="Sella_sito_free";


	public NewChatInfo getNewChatInfo() {
		return newChatInfo;
	}

	public void setNewChatInfo(NewChatInfo newChatInfo) {
		this.newChatInfo = newChatInfo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
