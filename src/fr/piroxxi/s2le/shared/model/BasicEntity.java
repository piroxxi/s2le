package fr.piroxxi.s2le.shared.model;

import java.io.Serializable;


public class BasicEntity implements Serializable{
	private static final long serialVersionUID = -6142397814553223400L;
	
	private String id;
	private Long version;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Long version) {
		this.version = version;
	}
}
