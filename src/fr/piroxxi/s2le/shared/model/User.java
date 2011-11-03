package fr.piroxxi.s2le.shared.model;

public class User extends BasicEntity {
	private static final long serialVersionUID = -6045505019801539144L;
	
	private String name;
	private String password; // crypted
	private String email;

	public User(String name, String password, String email) {
		setId(name);
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public User() {

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
