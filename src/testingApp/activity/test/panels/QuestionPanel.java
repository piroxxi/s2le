package testingApp.activity.test.panels;

import javax.swing.JPanel;

import fr.piroxxi.s2le.model.question.Question;

public abstract class QuestionPanel<T extends Question> extends JPanel {
	public interface Delegate{
		void hasAnswered(boolean wasAnswerGood);
	}

	protected Delegate delegate;

	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	public abstract void showQuestion(T question);
}
