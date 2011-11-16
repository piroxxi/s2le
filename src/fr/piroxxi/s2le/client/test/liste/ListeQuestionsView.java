package fr.piroxxi.s2le.client.test.liste;

import fr.piroxxi.s2le.client.ui.View;
import fr.piroxxi.s2le.model.question.Question;

public interface ListeQuestionsView extends View<ListeQuestionsView.Delegate> {

	void showErrorPanel(String string);

	void hideErrorPanel();

	void setQuestions(Question[] result);

	public interface Delegate extends View.Delegate {
		void createQuestion();

		void editQuestion(Question selectedObject);
	}

}
