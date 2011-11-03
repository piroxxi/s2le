package fr.piroxxi.s2le.shared.model.question;

import fr.piroxxi.s2le.shared.model.Category;
import fr.piroxxi.s2le.shared.model.Difficulty;

public class Translation extends Question {
	private static final long serialVersionUID = -2403937865476180974L;

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

}
