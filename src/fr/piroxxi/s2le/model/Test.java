package fr.piroxxi.s2le.model;

import java.util.List;

import fr.piroxxi.s2le.model.question.Question;

public class Test extends BasicEntity {
	private List<Question> questions;
	private int currentQuestion;

	public Test(List<Question> questions) {
		this.questions = questions;
		this.currentQuestion = 0;
	}
}
