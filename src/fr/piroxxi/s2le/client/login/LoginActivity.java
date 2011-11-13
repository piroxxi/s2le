package fr.piroxxi.s2le.client.login;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.ui.SessionManager.SessionVerifier;

public class LoginActivity extends AbstractActivity implements
		LoginView.Delegate {

	private final ClientFactory factory;
	private LoginView view;

	@Inject
	public LoginActivity(ClientFactory factory) {
		this.factory = factory;
		this.view = factory.getLoginView();
		this.view.setDelegate(this);

		this.factory.getSessionManager().isLoggedIn(new SessionVerifier() {

			@Override
			public void isLoggedIn(boolean loggedIn) {
				if (loggedIn) {
					view.setConnectedUser(LoginActivity.this.factory
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
		factory.getSecurityService().login(userName, password,
				new AsyncCallback<String>() {

					@Override
					public void onSuccess(String sessionId) {
						if (sessionId != null) {
							factory.getSessionManager().setLoggedIn(userName,
									sessionId);
							view.setConnectedUser(userName);
						} else {
							Window.alert("Wrong username or password!");
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Failure : " + caught.getMessage());
					}
				});
	}

	@Override
	public void logout() {
		factory.getSessionManager().setLoggedOut();
		view.setConnectedUser(null);
	}

}
