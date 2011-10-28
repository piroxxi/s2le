package fr.piroxxi.s2le.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.piroxxi.s2le.client.login.LoginActivity;
import fr.piroxxi.s2le.client.login.LoginViewImpl;
import fr.piroxxi.s2le.shared.StoreService;
import fr.piroxxi.s2le.shared.StoreServiceAsync;
import fr.piroxxi.s2le.shared.security.SecurityService;
import fr.piroxxi.s2le.shared.security.SecurityServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class S2le implements EntryPoint {
//	private final ClientGinjector injector = GWT.create(ClientGinjector.class);
	private SimplePanel loginPanel = new SimplePanel();
	
	private SecurityServiceAsync secu = GWT.create(SecurityService.class);
	private StoreServiceAsync service = GWT.create(StoreService.class);

	public void onModuleLoad() {
		/* Lancement du module de login. */
//		LoginActivity loginActivity = injector.getLoginActivity();
		
		LoginActivity loginActivity = new LoginActivity(new LoginViewImpl(), secu);
		
		loginActivity.start(loginPanel, null);
		
		RootPanel.get("login").add(loginPanel);
//		loginActivity.start(loginPanel, injector.getEventBus());

	}

}
