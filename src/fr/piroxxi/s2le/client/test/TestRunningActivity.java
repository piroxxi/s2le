package fr.piroxxi.s2le.client.test;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.events.QuestionAnsweredEvent;
import fr.piroxxi.s2le.client.places.ResultTestPlace;
import fr.piroxxi.s2le.client.places.TestPlace;
import fr.piroxxi.s2le.client.test.questions.MultiChoicesQuestionPanel;
import fr.piroxxi.s2le.client.test.questions.SimpleQuestionPanel;
import fr.piroxxi.s2le.client.test.questions.TranslationQuestionPanel;
import fr.piroxxi.s2le.client.test.questions.YesNoQuestionPanel;
import fr.piroxxi.s2le.model.Test;
import fr.piroxxi.s2le.model.question.MultiChoicesQuestion;
import fr.piroxxi.s2le.model.question.Question;
import fr.piroxxi.s2le.model.question.SimpleQuestion;
import fr.piroxxi.s2le.model.question.Translation;
import fr.piroxxi.s2le.model.question.YesNoQuestion;

public class TestRunningActivity extends AbstractActivity implements
		TestRunningView.Delegate {

	private final ClientFactory clientFactory;
	private final TestRunningView view;
	private final SimplePanel panel;
	private Test test;

	public TestRunningActivity(ClientFactory clientFactory, TestPlace testPlace) {
		this.clientFactory = clientFactory;

		this.view = clientFactory.getTestRunningView();
		this.view.setDelegate(this);
		panel = this.view.getMainPanel();

		this.clientFactory.getStoreService().getTest(testPlace.getTestId(),
				new AsyncCallback<Test>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("(TestRunningActivity) du coup on a pas reussi a chopper le test :/ "
								+ caught);
					}

					@Override
					public void onSuccess(Test test) {
						TestRunningActivity.this.test = test;
						printQuestion();
					}
				});

	}

	private void printQuestion() {
		if (test == null) {
			return;
		}
		view.setTest(test.getId(), (test.getCurrentQuestionNumber() + 1) + "/"
				+ test.getSize());
		Question q = test.getCurrentQuestion();
		if (q instanceof Translation) {
			TranslationQuestionPanel questionPanel = new TranslationQuestionPanel();
			questionPanel.setDelegate(this);
			questionPanel.showQuestion((Translation) q);
			panel.setWidget(questionPanel);
		}
		if (q instanceof YesNoQuestion) {
			YesNoQuestionPanel questionPanel = new YesNoQuestionPanel();
			questionPanel.setDelegate(this);
			questionPanel.showQuestion((YesNoQuestion) q);
			panel.setWidget(questionPanel);
		}
		if (q instanceof MultiChoicesQuestion) {
			MultiChoicesQuestionPanel questionPanel = new MultiChoicesQuestionPanel();
			questionPanel.showQuestion((MultiChoicesQuestion) q);
			questionPanel.setDelegate(this);
			panel.setWidget(questionPanel);
		}
		if (q instanceof SimpleQuestion) {
			SimpleQuestionPanel questionPanel = new SimpleQuestionPanel();
			questionPanel.setDelegate(this);
			questionPanel.showQuestion((SimpleQuestion) q);
			panel.setWidget(questionPanel);
		}
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(this.view);
	}

	@Override
	public void hasAnswered(final boolean goodAnswer) {
		// On effectue la mise à jour de l'objet test coté client.
		final boolean moreQuestion = test.hasAnswered(goodAnswer);

		// On effectue la mise à jour de l'objet test coté serveur.
		this.clientFactory.getStoreService().hasAnswered(
				this.clientFactory.getSessionManager().getSessionId(),
				test.getId(), goodAnswer, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("(TestRunningActivity) rha bah mince alors!");
					}

					@Override
					public void onSuccess(Boolean result) {
						clientFactory.getEventBus().fireEvent(
								new QuestionAnsweredEvent(goodAnswer));
					}
				});

		if (!moreQuestion) {
			clientFactory.getPlaceController().goTo(
					new ResultTestPlace(test.getGoodAnswerScore() + " / "
							+ test.getSize()));
		} else {
			printQuestion();
		}
	}
}
