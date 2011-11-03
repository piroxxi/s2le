package fr.piroxxi.s2le.shared.model.question;

import fr.piroxxi.s2le.shared.model.Category;
import fr.piroxxi.s2le.shared.model.Difficulty;

/**
 * A question which answer is a word.
 */
public class SimpleQuestion extends Question {
	private static final long serialVersionUID = 878222614771232197L;

	private String question;
	private String answer;

	public SimpleQuestion() {
		super();
	}

	public SimpleQuestion(String creator, Category category,
			Difficulty difficulty, String question, String answer) {
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
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
