package testingApp.menu;

import javax.swing.JPanel;

import testingApp.RefreshingEvent;
import testingApp.ui.Activity;
import testingApp.ui.eventBus.EventBus;

import com.google.inject.Inject;

import fr.piroxxi.s2le.server.model.User;
import fr.piroxxi.s2le.storage.api.Storage;

public class ConnectionActivity extends Activity implements
		ConnectionView.Presenter {

	private final ConnectionView menuView;
	private final Storage storage;
	private final EventBus eventBus;

	@Inject
	public ConnectionActivity(EventBus eventBus, Storage storage,
			ConnectionView menuView) {
		this.eventBus = eventBus;
		this.storage = storage;
		this.menuView = menuView;
	}

	@Override
	public void startActivity(JPanel panel) {
		menuView.setPresenter(this);
		menuView.noUserLogged();
		panel.add(menuView);
	}

	@Override
	public String login(String name, String password) {
		User user = storage.getEntity(User.class, name);
		if (user == null) {
			return "Utilisateur inexistant";
		}

		if (user.getPassword().equals(password)) {
			menuView.userLoggedIn(user.getId());
			eventBus.publishEvent(RefreshingEvent.class, new RefreshingEvent());
			return null;
		} else {
			return "Mauvais mot de passe";
		}
	}

	@Override
	public void logout() {
		menuView.noUserLogged();
		eventBus.publishEvent(RefreshingEvent.class, new RefreshingEvent());
	}

}
