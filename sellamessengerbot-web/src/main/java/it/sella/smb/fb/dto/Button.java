package it.sella.smb.fb.dto;

public class Button {

	private String type="web_url";
	private String url="https://www.sella.it/";
	private String title="View More";


	public String getTitle() {
		return title;
	}
	public String getType() {
		return type;
	}
	public String getUrl() {
		return url;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUrl(String url) {
		this.url = url;
	}


}
