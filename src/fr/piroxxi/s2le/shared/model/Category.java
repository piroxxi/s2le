package fr.piroxxi.s2le.shared.model;

public class Category extends BasicEntity{
	private static final long serialVersionUID = 1799251823246647817L;
	
	private String parentId;
	private String type;

	public Category(String parentId, String type) {
		this.parentId = parentId;
		this.type = type;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the type
	 */
	public String getCategoryName() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
