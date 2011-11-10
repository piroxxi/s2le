package fr.piroxxi.s2le.server.security;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.piroxxi.s2le.model.User;
import fr.piroxxi.s2le.server.injector.ServerModule;
import fr.piroxxi.s2le.shared.security.SecurityService;
import fr.piroxxi.s2le.storage.api.Storage;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SecurityServiceImpl extends RemoteServiceServlet implements
		SecurityService {

	private Storage storage;
	private SessionManager sessionManager;

	public SecurityServiceImpl() {
		Injector injector = Guice.createInjector(new ServerModule());
		this.storage = injector.getInstance(Storage.class);
		this.sessionManager = injector.getInstance(SessionManager.class);

	}

	@Override
	public String login(String name, String password)
			throws IllegalArgumentException {
		User user = storage.getEntity(User.class, name);
		if (user == null) {
			return null;
		}
		if (!user.getPassword().equals(password)) {
			return null;
		}

		return sessionManager.createSession(name);
	}

}
