package fr.piroxxi.s2le.client.user.stats;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.events.LoggedInEvent;
import fr.piroxxi.s2le.client.events.LoggedOutEvent;
import fr.piroxxi.s2le.client.events.LoggingEventHandler;
import fr.piroxxi.s2le.client.ui.OperationCallback;
import fr.piroxxi.s2le.client.ui.SessionManager.SessionVerifier;
import fr.piroxxi.s2le.model.User;

public class UserStatistiquesActivity extends AbstractActivity implements
		UserStatistiquesView.Delegate, LoggingEventHandler {

	private final ClientFactory clientFactory;
	private UserStatistiquesView view;

	public UserStatistiquesActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

		this.clientFactory.getEventBus().addHandler(LoggedInEvent.TYPE, this);
		this.clientFactory.getEventBus().addHandler(LoggedOutEvent.TYPE, this);

		this.view = this.clientFactory.getUserStatistiquesView();
		this.view.setDelegate(this);

		this.clientFactory.getSessionManager().isLoggedIn(
				new SessionVerifier() {
					@Override
					public void isLoggedIn(boolean loggedIn) {
						if (loggedIn) {
							showUserStats();
						} else {
							view.showErrorPanel("Impossible de récupérer la liste des questions tant que vous n'êtes pas connecté(e).");
						}
					}
				});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

	private void showUserStats() {
		this.clientFactory.getStoreService().getUser(
				this.clientFactory.getSessionManager().getSessionId(),
				this.clientFactory.getSessionManager().getUserName(),
				new OperationCallback<User>() {

					@Override
					public void onSuccess(User result) {
						view.hideErrorPanel();
						view.showUserStats(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						view.showErrorPanel("Impossible de récupérer la liste des questions tant que vous n'êtes pas connecté(e).");
					}
				});
	}

	@Override
	public void loggedIn(LoggedInEvent loggedInEvent) {
		showUserStats();
	}

	@Override
	public void loggedOut(LoggedOutEvent loggedOutEvent) {
		view.showErrorPanel("Impossible de récupérer la liste des questions tant que vous n'êtes pas connecté(e).");
	}
}
