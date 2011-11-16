package fr.piroxxi.s2le.client.test.creation;

import fr.piroxxi.s2le.client.ui.View;
import fr.piroxxi.s2le.model.question.QuestionType;

public interface CreateQuestionView extends View<CreateQuestionView.Delegate> {
	public interface Delegate extends View.Delegate {
		void createQuestion(QuestionType questionType);
	}
}
