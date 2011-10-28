package fr.piroxxi.s2le.server.model.question;

import fr.piroxxi.s2le.server.model.Category;
import fr.piroxxi.s2le.server.model.Difficulty;

public class Translation extends Question {
	private String englishWord;
	private String frenchWord;

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
