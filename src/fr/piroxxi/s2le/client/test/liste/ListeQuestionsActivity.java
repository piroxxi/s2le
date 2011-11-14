package fr.piroxxi.s2le.client.test.liste;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.places.CreateQuestionPlace;
import fr.piroxxi.s2le.client.ui.SessionManager.SessionVerifier;
import fr.piroxxi.s2le.model.question.Question;

public class ListeQuestionsActivity extends AbstractActivity implements
		ListeQuestionsView.Delegate {

	private final ClientFactory clientFactory;
	private ListeQuestionsView view;

	public ListeQuestionsActivity(final ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.clientFactory.getSessionManager().isLoggedIn(new SessionVerifier() {

			@Override
			public void isLoggedIn(boolean loggedIn) {
				clientFactory.getStoreService().listeQuestions(
						clientFactory.getSessionManager().getSessionId(),
						new AsyncCallback<Question[]>() {

							@Override
							public void onSuccess(Question[] result) {
								view.setQuestions(result);
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("bon bah :/ on a pas reussi a récupérer la liste des questions :) ");
							}
						});
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
		this.clientFactory.getPlaceController().goTo(new CreateQuestionPlace());
	}

}
