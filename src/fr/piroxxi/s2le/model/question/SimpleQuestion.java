package fr.piroxxi.s2le.model.question;

import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;

/**
 * A question which answer is a word.
 */
public class SimpleQuestion extends Question {
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

	public SimpleQuestion(String serializedQuestion) {
//		return "SimpleQuestion||" + question + "||" + answer;
		String[] split = serializedQuestion.split("\\|\\|", -1);
		this.question = split[1];
		this.answer = split[2];
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

	@Override
	public String serialize() {
		return "SimpleQuestion||" + question + "||" + answer;
	}

	/**
	 * To string d'une question simple.
	 */
	@Override
	public String toString() {
		return "SimpleQuestion : " + question + " => " + answer;
	}
}
