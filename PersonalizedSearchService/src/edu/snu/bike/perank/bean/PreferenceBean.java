package edu.snu.bike.perank.bean;

public class PreferenceBean {
	public static String TOPIC="topic";
	public static String LINK="link";
	private String type;
	private String[] inlink;
	public String[] getInlink() {
		return inlink;
	}
	public void setInlink(String[] inlink) {
		this.inlink = inlink;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
