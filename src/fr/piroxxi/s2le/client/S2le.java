package fr.piroxxi.s2le.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.piroxxi.s2le.client.injector.ClientGinjector;
import fr.piroxxi.s2le.client.login.LoginActivity;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class S2le implements EntryPoint {
	private final ClientGinjector injector = GWT.create(ClientGinjector.class);
	private SimplePanel loginPanel = new SimplePanel();

	public void onModuleLoad() {
		/* Lancement du module de login. */
		LoginActivity loginActivity = injector.getLoginActivity();
		RootPanel.get("login").add(loginPanel);
		loginActivity.start(loginPanel, injector.getEventBus());

	}

}
