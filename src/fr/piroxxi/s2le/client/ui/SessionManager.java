package fr.piroxxi.s2le.client.ui;

import fr.piroxxi.s2le.client.ClientFactory;

public class SessionManager {

	@SuppressWarnings("unused")
	private final ClientFactory clientFactory;

	private String sessionId = null;
	private String userName = null;

	public SessionManager(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	public String getUserName() {
		return userName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setLoggedIn(String userName, String sessionId) {
		this.userName = userName;
		this.sessionId = sessionId;
	}

	public void setLoggedOut() {
		this.userName = null;
		this.sessionId = null;
	}
}
