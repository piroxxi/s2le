package fr.piroxxi.s2le.client.login;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.events.LoggedInEvent;
import fr.piroxxi.s2le.client.events.LoggedOutEvent;
import fr.piroxxi.s2le.client.events.LoggingEventHandler;
import fr.piroxxi.s2le.client.ui.OperationCallback;
import fr.piroxxi.s2le.client.ui.SessionManager.SessionVerifier;

public class LoginActivity extends AbstractActivity implements
		LoginView.Delegate {

	private final ClientFactory clientFactory;
	private LoginView view;

	@Inject
	public LoginActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

		this.view = clientFactory.getLoginView();
		this.view.setDelegate(this);

		this.clientFactory.getSessionManager().isLoggedIn(
				new SessionVerifier() {

					@Override
					public void isLoggedIn(boolean loggedIn) {
						if (loggedIn) {
							view.setConnectedUser(LoginActivity.this.clientFactory
									.getSessionManager().getUserName());
						} else {
							view.setConnectedUser(null);
						}
					}
				});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(this.view);
	}

	@Override
	public void login(final String userName, String password) {
		clientFactory
				.getSecurityService()
				.login(userName,
						password,
						new OperationCallback<String>(this.clientFactory,
								"(LoginActivity) Erreure lors de la verification de la tentative de logging") {
							@Override
							public void onSuccess(String sessionId) {
								if (sessionId != null) {
									clientFactory.getSessionManager()
											.setLoggedIn(userName, sessionId);
									view.setConnectedUser(userName);
								} else {
									Window.alert("Wrong username or password!");
								}
							}
						});
	}

	@Override
	public void logout() {
		clientFactory.getSessionManager().setLoggedOut();
		view.setConnectedUser(null);
	}
}
