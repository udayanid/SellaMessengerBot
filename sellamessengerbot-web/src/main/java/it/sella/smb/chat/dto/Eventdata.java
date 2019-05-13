package it.sella.smb.chat.dto;


public class Eventdata {

	private String name="message";
	private String value;

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Eventdatum [name=" + name + ", value=" + value + "]";
	}



}
