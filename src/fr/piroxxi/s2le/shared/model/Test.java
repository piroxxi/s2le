package fr.piroxxi.s2le.shared.model;

import java.util.List;

import fr.piroxxi.s2le.shared.model.question.Question;

public class Test extends BasicEntity {
	private static final long serialVersionUID = 3782532160882054530L;

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

	public Question getNextQuestion() {
		if (currentQuestion >= questions.size()) {
			return null;
		}
		Question q = questions.get(currentQuestion);
		currentQuestion++;
		return q;
	}

	public void addGoodAnswer() {
		goodAnswer++;
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
}
