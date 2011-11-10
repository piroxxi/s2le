package fr.piroxxi.s2le.model;

public class User extends BasicEntity {
	private String name;
	private String password; // crypted
	private String email;

	private int nbTestsCommencees = 0;
	private int nbTestsFinis = 0;
	private int nbQuestionsRepondues = 0;
	private int nbQuestionsJustes = 0;

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

	
	
	public void startATest() {
		this.nbTestsCommencees++;
	}

	public void finishedATest() {
		this.nbTestsFinis++;
	}

	public void hasAnswered(boolean answerRight) {
		nbQuestionsRepondues++;
		if (answerRight) {
			nbQuestionsJustes++;
		}
	}
	
	
	

	/**
	 * @return the nbTestsCommencees
	 */
	public int getNbTestsCommencees() {
		return nbTestsCommencees;
	}

	/**
	 * @return the nbTestsFinis
	 */
	public int getNbTestsFinis() {
		return nbTestsFinis;
	}

	/**
	 * @return the nbQuestionsRepondues
	 */
	public int getNbQuestionsRepondues() {
		return nbQuestionsRepondues;
	}

	/**
	 * @return the nbQuestionsJustes
	 */
	public int getNbQuestionsJustes() {
		return nbQuestionsJustes;
	}
}
