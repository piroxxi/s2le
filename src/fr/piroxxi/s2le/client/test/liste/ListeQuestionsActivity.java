package fr.piroxxi.s2le.client.test.liste;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.events.LoggedInEvent;
import fr.piroxxi.s2le.client.events.LoggedOutEvent;
import fr.piroxxi.s2le.client.events.LoggingEventHandler;
import fr.piroxxi.s2le.client.places.StartQuestionCreationPlace;
import fr.piroxxi.s2le.client.places.EditQuestionPlace;
import fr.piroxxi.s2le.client.ui.SessionManager.SessionVerifier;
import fr.piroxxi.s2le.model.question.Question;

public class ListeQuestionsActivity extends AbstractActivity implements
		ListeQuestionsView.Delegate, LoggingEventHandler{

	private final ClientFactory clientFactory;
	private ListeQuestionsView view;

	public ListeQuestionsActivity(final ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		
		this.clientFactory.getEventBus().addHandler(LoggedInEvent.TYPE, this);
		this.clientFactory.getEventBus().addHandler(LoggedOutEvent.TYPE, this);
		
		this.clientFactory.getSessionManager().isLoggedIn(
				new SessionVerifier() {

					@Override
					public void isLoggedIn(boolean loggedIn) {
						showQuestion();
					}
				});
	}

	private void showQuestion() {
		this.clientFactory.getStoreService().listeQuestions(
				this.clientFactory.getSessionManager().getSessionId(),
				new AsyncCallback<Question[]>() {

					@Override
					public void onSuccess(Question[] result) {
						view.hideErrorPanel();
						view.setQuestions(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						view.showErrorPanel("Impossible de récupérer la liste des questions tant que vous n'êtes pas connecté(e).");
					}
				});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.view = clientFactory.getListeTestView();
		view.setDelegate(this);
		panel.setWidget(view);
	}

	@Override
	public void createQuestion() {
		this.clientFactory.getPlaceController().goTo(new StartQuestionCreationPlace());
	}

	@Override
	public void editQuestion(Question selectedObject) {
		this.clientFactory.getPlaceController().goTo(
				new EditQuestionPlace(selectedObject.getId()));
	}

	@Override
	public void loggedIn(LoggedInEvent loggedInEvent) {
		showQuestion();
	}

	@Override
	public void loggedOut(LoggedOutEvent loggedOutEvent) {
		view.showErrorPanel("Impossible de récupérer la liste des questions tant que vous n'êtes pas connecté(e).");
	}

}
