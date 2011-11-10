package fr.piroxxi.s2le.model.question;

import fr.piroxxi.s2le.model.BasicEntity;
import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;

public abstract class Question extends BasicEntity {
	private String creatorId;
	private Category category;
	private Difficulty difficulty;

	public Question() {

	}

	public Question(String creatorId, Category category, Difficulty difficulty) {
		this.creatorId = creatorId;
		this.category = category;
		this.difficulty = difficulty;
	}

	/**
	 * @return the creatorId
	 */
	public String getCreatorId() {
		return creatorId;
	}

	/**
	 * @param creatorId
	 *            the creatorId to set
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty
	 *            the difficulty to set
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public abstract String serialize();
}
