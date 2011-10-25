package testingApp.activity.test;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import testingApp.RefreshingEvent;
import testingApp.activity.test.panels.QuestionPanel.Delegate;
import testingApp.activity.test.panels.TranslationPanel;
import testingApp.activity.test.panels.YesNoQuestionPanel;
import testingApp.ui.Activity;
import testingApp.ui.eventBus.EventBus;

import com.google.inject.Inject;

import fr.piroxxi.s2le.model.Test;
import fr.piroxxi.s2le.model.question.Question;
import fr.piroxxi.s2le.model.question.Translation;
import fr.piroxxi.s2le.model.question.YesNoQuestion;

public class TestExecutionActivity extends Activity implements Delegate {

	private Test test;
	private JPanel thisPanel;
	private final EventBus eventBus;
	private Question currentQuestion;

	@Inject
	public TestExecutionActivity(EventBus eventBus) {
		this.eventBus = eventBus;
		thisPanel = new JPanel();
	}

	public void setTest(Test test) {
		this.test = test;
	}

	@Override
	public void startActivity(JPanel panel) {
		if (test != null) {
			panel.add(thisPanel);
			nextQuestion();
		}
	}

	private void nextQuestion() {
		currentQuestion = test.getNextQuestion();
		if (currentQuestion != null) {
			if (currentQuestion instanceof YesNoQuestion) {
				YesNoQuestionPanel panel = new YesNoQuestionPanel(test.chrono());
				panel.showQuestion((YesNoQuestion) currentQuestion);
				panel.setDelegate(this);
				thisPanel.removeAll();
				thisPanel.add(panel);
			} else if (currentQuestion instanceof Translation) {
				TranslationPanel panel = new TranslationPanel(test.chrono());
				panel.showQuestion((Translation) currentQuestion);
				panel.setDelegate(this);
				thisPanel.removeAll();
				thisPanel.add(panel);
			} else {
				thisPanel.removeAll();
				JButton button = new JButton("" + currentQuestion);
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						nextQuestion();
					}
				});
				thisPanel.removeAll();
				thisPanel.add(button);
			}

		} else {
			thisPanel.removeAll();
			thisPanel.add(new Label("you just finished the Test. You got "
					+ test.getGoodAnswerScore() + "/" + test.getSize()));
		}
		eventBus.publishEvent(RefreshingEvent.class, new RefreshingEvent());
	}

	@Override
	public void hasAnswered(boolean wasAnswerGood) {
		test.addGoodAnswer();
		nextQuestion();
	}
}
