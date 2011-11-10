package fr.piroxxi.s2le.client.test.questions;

import com.google.gwt.user.client.ui.IsWidget;

import fr.piroxxi.s2le.model.question.Question;

public interface QuestionPanel<T extends Question> extends IsWidget {

	void setDelegate(Delegate delegate);

	void showQuestion(T question);

	public interface Delegate {
		void hasAnswered(boolean goodAnswer);
	}
}
