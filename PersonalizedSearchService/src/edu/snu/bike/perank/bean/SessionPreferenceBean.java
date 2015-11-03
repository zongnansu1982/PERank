package edu.snu.bike.perank.bean;

public class SessionPreferenceBean {
private Integer id;
private String[] topics;
private String[] links;
public String[] getLinks() {
	return links;
}
public void setLinks(String[] links) {
	this.links = links;
}
public String[] getTopics() {
	return topics;
}
public void setTopics(String[] topics) {
	this.topics = topics;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}

}
