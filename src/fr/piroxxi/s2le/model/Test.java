package fr.piroxxi.s2le.model;

import java.util.List;

import fr.piroxxi.s2le.model.question.Question;

public class Test extends BasicEntity {
	private List<Question> questions;
	private int currentQuestion;
	private boolean isChrono;
	private int goodAnswer;

	public Test() {

	}

	public Test(List<Question> questions, boolean chrono) {
		this.questions = questions;
		this.currentQuestion = 0;
		this.isChrono = chrono;
	}

	/**
	 * @deprecated (en fait, je les utilises quelque part, alors je les
	 *             supprimes pas tout de suite, mais je le ferais :) ! )
	 */
	public Question getNextQuestion() {
		return null;
	}

	public Question getCurrentQuestion() {
		if (currentQuestion >= questions.size()) {
			return null;
		}
		return questions.get(currentQuestion);
	}

	/**
	 * @deprecated (en fait, je les utilises quelque part, alors je les
	 *             supprimes pas tout de suite, mais je le ferais :) ! )
	 */
	public void addGoodAnswer() {
	}

	public boolean hasAnswered(boolean goodAnswer) {
		if (goodAnswer) {
			this.goodAnswer++;
		}
		currentQuestion++;
		return currentQuestion < questions.size();
	}

	public int getGoodAnswerScore() {
		return goodAnswer;
	}

	public int getSize() {
		return questions.size();
	}

	public boolean chrono() {
		return isChrono;
	}

	public int getCurrentQuestionNumber() {
		return currentQuestion;
	}

}
