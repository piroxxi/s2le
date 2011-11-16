package fr.piroxxi.s2le.model.question;

import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;

public class YesNoQuestion extends Question {
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

	public YesNoQuestion(String serializedQuestion) {
		// return "YesNoQuestion||" + question + "||" + answer;
		String[] split = serializedQuestion.split("\\|\\|", -1);
		this.question = split[1];
		this.answer = Boolean.parseBoolean(split[2]);
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

	@Override
	public String serialize() {
		return "YesNoQuestion||" + question + "||" + answer;
	}

	/**
	 * To string d'une question oui/non.
	 */
	@Override
	public String toString() {
		return "Translation : " + question + " => " + answer;
	}

	@Override
	public String getResume() {
		return getQuestion();
	}

}
