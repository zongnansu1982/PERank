package edu.snu.bike.perank.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * Linkintresttable generated by MyEclipse Persistence Tools
 */

public class LinkInterestTable implements java.io.Serializable {

	// Fields

	private Integer id;

	private String link;

	private Set preferencelinkmappings = new HashSet(0);

	// Constructors

	/** default constructor */
	public LinkInterestTable() {
	}

	/** minimal constructor */
	public LinkInterestTable(String link) {
		this.link = link;
	}

	/** full constructor */
	public LinkInterestTable(String link, Set preferencelinkmappings) {
		this.link = link;
		this.preferencelinkmappings = preferencelinkmappings;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Set getPreferencelinkmappings() {
		return this.preferencelinkmappings;
	}

	public void setPreferencelinkmappings(Set preferencelinkmappings) {
		this.preferencelinkmappings = preferencelinkmappings;
	}

}