package fr.piroxxi.s2le.shared.model.question;

import fr.piroxxi.s2le.shared.model.Category;
import fr.piroxxi.s2le.shared.model.Difficulty;

public class YesNoQuestion extends Question {
	private static final long serialVersionUID = -2643435622228176861L;

	private String question;
	private boolean answer;

	public YesNoQuestion() {
		super();
	}

	public YesNoQuestion(String creator, Category category,
			Difficulty difficulty, String question, boolean answer) {
		super(creator, category, difficulty);
		this.question = question;
		this.answer = answer;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the answer
	 */
	public boolean isAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
}
