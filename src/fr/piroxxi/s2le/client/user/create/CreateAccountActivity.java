package fr.piroxxi.s2le.client.user.create;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;

public class CreateAccountActivity extends AbstractActivity implements
		CreateAccountView.Delegate {

	private ClientFactory clientFactory;
	private CreateAccountView view;

	public CreateAccountActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.view = clientFactory.getCreateAccountView();
		view.setDelegate(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

	@Override
	public void createAccount(String name, String password, String email) {
		Window.alert("on va créer un nouveau compte pour l'utilisateur " + name
				+ " " + password + " " + email);
		clientFactory.getStoreService();
	}

}
