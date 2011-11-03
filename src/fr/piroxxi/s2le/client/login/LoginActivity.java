package fr.piroxxi.s2le.client.login;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fr.piroxxi.s2le.client.ClientFactory;

public class LoginActivity extends AbstractActivity implements
		LoginView.Delegate {

	private final ClientFactory factory;
	private LoginView view;

	@Inject
	public LoginActivity(ClientFactory factory) {
		this.factory = factory;
		/**
		 * La, c'est le moment de vérifier qu'on est loggé !
		 */
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.view = factory.getLoginView();
		panel.setWidget(this.view);
		this.view.setDelegate(this);
	}

	@Override
	public void login(final String userName, String password) {
		factory.getSecurityService().login(userName, password,
				new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						if (result != null) {
							// TODO(raphael) Peut être que la on devrait
							// recevoir un
							// objet session, avec quelques infos sur
							// l'utilisateur.
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
		view.setConnectedUser(null);
		// TODO(raphael) mettre en place un vrai logout.
	}

}
