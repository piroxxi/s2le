package fr.piroxxi.s2le.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

import fr.piroxxi.s2le.client.asside.AssideView;
import fr.piroxxi.s2le.client.asside.AssideViewImpl;
import fr.piroxxi.s2le.client.asside.advise.AdvisorView;
import fr.piroxxi.s2le.client.asside.advise.AdvisorViewImpl;
import fr.piroxxi.s2le.client.hello.HelloView;
import fr.piroxxi.s2le.client.hello.HelloViewImpl;
import fr.piroxxi.s2le.client.login.LoginView;
import fr.piroxxi.s2le.client.login.LoginViewImpl;
import fr.piroxxi.s2le.client.menu.MenuView;
import fr.piroxxi.s2le.client.menu.MenuViewImpl;
import fr.piroxxi.s2le.client.test.CreateTestView;
import fr.piroxxi.s2le.client.test.CreateTestViewImpl;
import fr.piroxxi.s2le.client.test.ResultTestView;
import fr.piroxxi.s2le.client.test.ResultTestViewImpl;
import fr.piroxxi.s2le.client.test.TestRunningView;
import fr.piroxxi.s2le.client.test.TestRunningViewImpl;
import fr.piroxxi.s2le.client.test.liste.ListeQuestionsView;
import fr.piroxxi.s2le.client.test.liste.ListeQuestionsViewImpl;
import fr.piroxxi.s2le.client.ui.SessionManager;
import fr.piroxxi.s2le.shared.StoreService;
import fr.piroxxi.s2le.shared.StoreServiceAsync;
import fr.piroxxi.s2le.shared.security.SecurityService;
import fr.piroxxi.s2le.shared.security.SecurityServiceAsync;

public class ClientFactoryImpl implements ClientFactory {
	private final SimpleEventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(
			(com.google.web.bindery.event.shared.EventBus) eventBus);
	private final SessionManager sessionManager = new SessionManager(this);

	// services
	private SecurityServiceAsync securityServiceAsync = GWT
			.create(SecurityService.class);
	private StoreServiceAsync storeServiceAsync = GWT
			.create(StoreService.class);

	// views
	private final AssideView assideView = new AssideViewImpl();
	private final LoginView loginView = new LoginViewImpl();
	private final ListeQuestionsView listeTestView = new ListeQuestionsViewImpl();
	private final HelloView helloView = new HelloViewImpl();
	private final MenuView menuView = new MenuViewImpl();
	private final AdvisorView advisorView = new AdvisorViewImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public SecurityServiceAsync getSecurityService() {
		return securityServiceAsync;
	}

	@Override
	public StoreServiceAsync getStoreService() {
		return storeServiceAsync;
	}

	@Override
	public AssideView getAssideView() {
		return assideView;
	}

	@Override
	public LoginView getLoginView() {
		return loginView;
	}

	@Override
	public ListeQuestionsView getListeTestView() {
		return listeTestView;
	}

	@Override
	public HelloView getHelloView() {
		return helloView;
	}

	@Override
	public MenuView getMenuView() {
		return menuView;
	}

	@Override
	public CreateTestView getCreateTestView() {
		return new CreateTestViewImpl();
	}

	@Override
	public TestRunningView getTestRunningView() {
		return new TestRunningViewImpl();
	}

	@Override
	public SessionManager getSessionManager() {
		return sessionManager;
	}

	@Override
	public ResultTestView getResultTestView() {
		return new ResultTestViewImpl();
	}

	@Override
	public AdvisorView getAdvisorView() {
		return advisorView;
	}
}
