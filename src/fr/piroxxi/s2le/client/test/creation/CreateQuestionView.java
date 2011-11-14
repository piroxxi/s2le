package fr.piroxxi.s2le.client.test.creation;

import fr.piroxxi.s2le.client.ui.View;
import fr.piroxxi.s2le.model.question.Question;

public interface CreateQuestionView extends View<CreateQuestionView.Delegate> {
	public interface Delegate extends View.Delegate {
		public void createQuestion(Question question);
	}
}
