package fr.piroxxi.s2le.model.question;

import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;

public class Translation extends Question {
	private String englishWord;
	private String frenchWord;

	public Translation() {
		super();
	}

	public Translation(String creatorId, Category category,
			Difficulty difficulty, String englishWord, String frenchWord) {
		super(creatorId, category, difficulty);
		this.englishWord = englishWord;
		this.frenchWord = frenchWord;
	}

	public Translation(String serializedQuestion) {
		// return "Translation||" + englishWord + "||"
		// + frenchWord;
		String[] split = serializedQuestion.split("\\|\\|", -1);
		this.englishWord = split[1];
		this.frenchWord = split[2];
	}

	/**
	 * @return the englishWord
	 */
	public String getEnglishWord() {
		return englishWord;
	}

	/**
	 * @param englishWord
	 *            the englishWord to set
	 */
	public void setEnglishWord(String englishWord) {
		this.englishWord = englishWord;
	}

	/**
	 * @return the frenchWord
	 */
	public String getFrenchWord() {
		return frenchWord;
	}

	/**
	 * @param frenchWord
	 *            the frenchWord to set
	 */
	public void setFrenchWord(String frenchWord) {
		this.frenchWord = frenchWord;
	}

	@Override
	public String serialize() {
		return "Translation||" + englishWord + "||" + frenchWord;
	}

	/**
	 * To string d'une question de traduction.
	 */
	@Override
	public String toString() {
		return "Translation : " + englishWord + " => " + frenchWord;
	}

}
