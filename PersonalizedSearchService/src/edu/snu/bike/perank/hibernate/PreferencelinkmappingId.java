package edu.snu.bike.perank.hibernate;

/**
 * PreferencelinkmappingId generated by MyEclipse Persistence Tools
 */

public class PreferencelinkmappingId implements java.io.Serializable {

	// Fields

	private Preferencetable preferencetable;

	private Linkintresttable linkintresttable;

	// Constructors

	/** default constructor */
	public PreferencelinkmappingId() {
	}

	/** full constructor */
	public PreferencelinkmappingId(Preferencetable preferencetable,
			Linkintresttable linkintresttable) {
		this.preferencetable = preferencetable;
		this.linkintresttable = linkintresttable;
	}

	// Property accessors

	public Preferencetable getPreferencetable() {
		return this.preferencetable;
	}

	public void setPreferencetable(Preferencetable preferencetable) {
		this.preferencetable = preferencetable;
	}

	public Linkintresttable getLinkintresttable() {
		return this.linkintresttable;
	}

	public void setLinkintresttable(Linkintresttable linkintresttable) {
		this.linkintresttable = linkintresttable;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PreferencelinkmappingId))
			return false;
		PreferencelinkmappingId castOther = (PreferencelinkmappingId) other;

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