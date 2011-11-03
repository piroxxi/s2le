package fr.piroxxi.s2le.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

import fr.piroxxi.s2le.client.asside.AssideActivity;
import fr.piroxxi.s2le.client.login.LoginActivity;
import fr.piroxxi.s2le.client.menu.MenuActivity;
import fr.piroxxi.s2le.client.places.HelloPlace;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class S2le implements EntryPoint {
	// Bon bah là, GIN marche pas ! :/
	// private final ClientGinjector injector =
	// GWT.create(ClientGinjector.class);

	private SimplePanel loginPanel = new SimplePanel();
	private SimplePanel assidePanel = new SimplePanel();
	private SimplePanel mainPanel = new SimplePanel();
	private SimplePanel menuPanel = new SimplePanel();

	private ClientFactory factory = GWT.create(ClientFactory.class);

	public void onModuleLoad() {
		EventBus eventBus = factory.getEventBus();
		PlaceController placeController = factory.getPlaceController();

		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper activityMapper = new ApplicationActivityMapper(factory);
		ActivityManager activityManager = new ActivityManager(activityMapper,
				eventBus);
		activityManager.setDisplay(mainPanel);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		ApplicationPlaceHistoryMapper historyMapper = GWT
				.create(ApplicationPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus, new HelloPlace());

		RootPanel.get("content").add(mainPanel);

		// Goes to the place represented on URL else default place
		historyHandler.handleCurrentHistory();

		/* Lancement des trois modules. */
		LoginActivity loginActivity = new LoginActivity(factory);
		loginActivity.start(loginPanel, factory.getEventBus());
		RootPanel.get("login").add(loginPanel);

		AssideActivity assideActivity = new AssideActivity(factory);
		assideActivity.start(assidePanel, factory.getEventBus());
		RootPanel.get("asside").add(assidePanel);

		MenuActivity menuActivity = new MenuActivity(factory);
		menuActivity.start(menuPanel, factory.getEventBus());
		RootPanel.get("menu").add(menuPanel);
	}

}
