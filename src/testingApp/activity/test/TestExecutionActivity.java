package testingApp.activity.test;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import testingApp.RefreshingEvent;
import testingApp.activity.test.panels.MultyChoicesQuestionPanel;
import testingApp.activity.test.panels.QuestionPanel.Delegate;
import testingApp.activity.test.panels.TranslationPanel;
import testingApp.activity.test.panels.YesNoQuestionPanel;
import testingApp.ui.Activity;
import testingApp.ui.eventBus.EventBus;
import testingApp.ui.placeControler.PlaceControler;

import com.google.inject.Inject;

import fr.piroxxi.s2le.model.Test;
import fr.piroxxi.s2le.model.question.MultiChoicesQuestion;
import fr.piroxxi.s2le.model.question.Question;
import fr.piroxxi.s2le.model.question.Translation;
import fr.piroxxi.s2le.model.question.YesNoQuestion;

public class TestExecutionActivity extends Activity implements Delegate {

	private Test test;
	private JPanel thisPanel;
	private final EventBus eventBus;
	private Question currentQuestion;
	private final PlaceControler placeControler;

	@Inject
	public TestExecutionActivity(PlaceControler placeControler, EventBus eventBus) {
		this.placeControler = placeControler;
		this.eventBus = eventBus;
		thisPanel = new JPanel();
	}

	public void setTest(Test test) {
		this.test = test;
	}

	@Override
	public void startActivity(JPanel panel) {
		if (test != null) {
			panel.removeAll();
			panel.add(thisPanel);
			nextQuestion();
		}
	}

	@SuppressWarnings("deprecation")
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
			} else if (currentQuestion instanceof MultiChoicesQuestion) {
				MultyChoicesQuestionPanel panel = new MultyChoicesQuestionPanel(test.chrono());
				panel.showQuestion((MultiChoicesQuestion) currentQuestion);
				panel.setDelegate(this);
				thisPanel.removeAll();
				thisPanel.add(panel);
			}else {
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
			JPanel p = new JPanel();
			p.add(new Label("You just finished the Test. You got "
					+ test.getGoodAnswerScore() + "/" + test.getSize()));
			JButton finish = new JButton("retour");
			finish.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					finishTest();
				}
			});
			p.add(finish);
			thisPanel.removeAll();
			thisPanel.add(p);
		}
		eventBus.publishEvent(RefreshingEvent.class, new RefreshingEvent());
	}

	protected void finishTest() {
		System.out.println("fin du test");
		placeControler.goTo(null);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void hasAnswered(boolean wasAnswerGood) {
		if (wasAnswerGood){
			test.addGoodAnswer();
			System.out.println("la question "+currentQuestion+" a étée bien répondu!");
		}else{
			System.out.println("la question "+currentQuestion+" a étée mal répondu!");
		}
		nextQuestion();
	}
}
