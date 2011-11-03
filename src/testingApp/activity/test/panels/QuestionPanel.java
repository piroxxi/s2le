package testingApp.activity.test.panels;

import javax.swing.JPanel;

import fr.piroxxi.s2le.shared.model.question.Question;

public abstract class QuestionPanel<T extends Question> extends JPanel {
	private static final long serialVersionUID = 5582862118988858256L;

	public interface Delegate{
		void hasAnswered(boolean wasAnswerGood);
	}

	protected Delegate delegate;

	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	public abstract void showQuestion(T question);
}
