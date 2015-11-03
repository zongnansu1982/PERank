package edu.snu.bike.perank.hibernate;

/**
 * PreferencelinkmappingId generated by MyEclipse Persistence Tools
 */

public class PreferenceLinkMappingId implements java.io.Serializable {

	// Fields

	private PreferenceTable preferencetable;

	private LinkInterestTable linkintresttable;

	// Constructors

	/** default constructor */
	public PreferenceLinkMappingId() {
	}

	/** full constructor */
	public PreferenceLinkMappingId(PreferenceTable preferencetable,
			LinkInterestTable linkintresttable) {
		this.preferencetable = preferencetable;
		this.linkintresttable = linkintresttable;
	}

	// Property accessors

	public PreferenceTable getPreferencetable() {
		return this.preferencetable;
	}

	public void setPreferencetable(PreferenceTable preferencetable) {
		this.preferencetable = preferencetable;
	}

	public LinkInterestTable getLinkintresttable() {
		return this.linkintresttable;
	}

	public void setLinkintresttable(LinkInterestTable linkintresttable) {
		this.linkintresttable = linkintresttable;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PreferenceLinkMappingId))
			return false;
		PreferenceLinkMappingId castOther = (PreferenceLinkMappingId) other;

		return ((this.getPreferencetable() == castOther.getPreferencetable()) || (this
				.getPreferencetable() != null
				&& castOther.getPreferencetable() != null && this
				.getPreferencetable().equals(castOther.getPreferencetable())))
				&& ((this.getLinkintresttable() == castOther
						.getLinkintresttable()) || (this.getLinkintresttable() != null
						&& castOther.getLinkintresttable() != null && this
						.getLinkintresttable().equals(
								castOther.getLinkintresttable())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPreferencetable() == null ? 0 : this.getPreferencetable()
						.hashCode());
		result = 37
				* result
				+ (getLinkintresttable() == null ? 0 : this
						.getLinkintresttable().hashCode());
		return result;
	}

}