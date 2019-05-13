package it.sella.smb.fb.dto;

import java.util.ArrayList;
import java.util.List;

public class Element {
	private String url;
	private List<Button> buttons = new ArrayList<Button>();

	public Element() {

	}
	public Element(String url) {
		this.url = url;
		buttons.add(new Button());
	}

	public List<Button> getButtons() {
		return buttons;
	}
	public String getUrl() {
		return url;
	}
	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}
	public void setUrl(String url) {
		this.url = url;
	}



}
