package it.sella.smb.fb.dto;

import java.util.ArrayList;
import java.util.List;

public class Payload {

	private String template_type="open_graph";
	private List<Element> elements = new ArrayList<Element>();

	public Payload() {

	}

	public Payload(String url) {
		final Element element = new Element(url);
		elements.add(element);
	}

	public List<Element> getElements() {
		return elements;
	}
	public String getTemplate_type() {
		return template_type;
	}
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}
	public void setTemplate_type(String template_type) {
		this.template_type = template_type;
	}




}
