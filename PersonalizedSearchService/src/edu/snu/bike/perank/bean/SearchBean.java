package edu.snu.bike.perank.bean;

public class SearchBean {
	private String Link;
	private String preference;
	public String getLink() {
		return Link;
	}
	public void setLink(String link) {
		Link = link;
	}
	public String getOutLink() {
		return outLink;
	}
	public void setOutLink(String outLink) {
		this.outLink = outLink;
	}
	public Double getLocalizedFingerPrint() {
		return LocalizedFingerPrint;
	}
	public void setLocalizedFingerPrint(Double localizedFingerPrint) {
		LocalizedFingerPrint = localizedFingerPrint;
	}
	private String outLink;
	private Double LocalizedFingerPrint;
	public String getPreference() {
		return preference;
	}
	public void setPreference(String preference) {
		this.preference = preference;
	}
	
}
