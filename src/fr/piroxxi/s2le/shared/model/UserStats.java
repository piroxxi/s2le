package fr.piroxxi.s2le.shared.model;

public class UserStats {
	private int bonnesReponses;
	private int totalQuestion;
	private String userName;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the bonnesReponses
	 */
	public int getBonnesReponses() {
		return bonnesReponses;
	}

	/**
	 * @param bonnesReponses
	 *            the bonnesReponses to set
	 */
	public void setBonnesReponses(int bonnesReponses) {
		this.bonnesReponses = bonnesReponses;
	}

	/**
	 * @return the totalQuestion
	 */
	public int getTotalQuestion() {
		return totalQuestion;
	}

	/**
	 * @param totalQuestion
	 *            the totalQuestion to set
	 */
	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}
}
