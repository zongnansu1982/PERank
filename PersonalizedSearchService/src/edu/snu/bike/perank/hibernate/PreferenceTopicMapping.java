package edu.snu.bike.perank.hibernate;

/**
 * Preferencetopicmapping generated by MyEclipse Persistence Tools
 */

public class PreferenceTopicMapping implements java.io.Serializable {

	// Fields

	private PreferenceTopicMappingId id;

	// Constructors

	/** default constructor */
	public PreferenceTopicMapping() {
	}

	/** full constructor */
	public PreferenceTopicMapping(PreferenceTopicMappingId id) {
		this.id = id;
	}

	// Property accessors

	public PreferenceTopicMappingId getId() {
		return this.id;
	}

	public void setId(PreferenceTopicMappingId id) {
		this.id = id;
	}

}