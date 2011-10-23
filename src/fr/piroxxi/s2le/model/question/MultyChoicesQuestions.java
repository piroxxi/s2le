package fr.piroxxi.s2le.model.question;

import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;

public class MultyChoicesQuestions extends Question {
	private String question;
	private String[] answers;
	private int rightAnswer;

	public MultyChoicesQuestions(String creator, Category category,
			Difficulty difficulty, String question, String[] answers,
			int rightAnswer) {
		super(creator, category, difficulty);
		this.question = question;
		this.answers = answers;
		this.rightAnswer = rightAnswer;
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
	 * @return the answers
	 */
	public String[] getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 *            the answers to set
	 */
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	/**
	 * @return the rightAnswer
	 */
	public int getRightAnswer() {
		return rightAnswer;
	}

	/**
	 * @param rightAnswer
	 *            the rightAnswer to set
	 */
	public void setRightAnswer(int rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
}
