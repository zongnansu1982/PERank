package edu.snu.bike.perank.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * Topicintresttable generated by MyEclipse Persistence Tools
 */

public class Topicintresttable implements java.io.Serializable {

	// Fields

	private Integer id;

	private String topic;

	private Set preferencetopicmappings = new HashSet(0);

	// Constructors

	/** default constructor */
	public Topicintresttable() {
	}

	/** minimal constructor */
	public Topicintresttable(String topic) {
		this.topic = topic;
	}

	/** full constructor */
	public Topicintresttable(String topic, Set preferencetopicmappings) {
		this.topic = topic;
		this.preferencetopicmappings = preferencetopicmappings;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Set getPreferencetopicmappings() {
		return this.preferencetopicmappings;
	}

	public void setPreferencetopicmappings(Set preferencetopicmappings) {
		this.preferencetopicmappings = preferencetopicmappings;
	}

}