package fr.piroxxi.s2le.client.ui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.piroxxi.s2le.client.ClientFactory;

public class SessionManager {
	public interface SessionVerifier {
		public void isLoggedIn(boolean loggedIn);
	}

	private ClientFactory clientFactory;
	private String sessionId = null;
	private String userName = null;
	private boolean loggedIn = false;
	private boolean isApplicationStarting = true;

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
		this.loggedIn = true;
	}

	public void setLoggedOut() {
		this.userName = null;
		this.sessionId = null;
		this.loggedIn = false;
	}

	/**
	 * Le léger inconvenient de cette fonction, c'est qu'il existe un moment ou
	 * l'on ne sait pas.
	 * 
	 * Lorsque l'on lance l'application (ie, on fait F5), on peut être connecté
	 * sans que la valeur présente ici soit valide. Il faut donc refaire une
	 * demande. Dans le cas ou la valeur que l'on a ici est viable, alors
	 * l'appel est synchrone. Sinon, il est asynchrone.
	 */
	public void isLoggedIn(final SessionVerifier sessionVerifier) {
		if (isApplicationStarting) {
			final String sessionId = null; // TODO lire un cookie

			if (sessionId != null) {
				sessionVerifier.isLoggedIn(false);
			} else {

				clientFactory.getSecurityService().verifySession(sessionId,
						new AsyncCallback<Boolean>() {

							@Override
							public void onSuccess(Boolean result) {
								sessionVerifier.isLoggedIn(result);
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("(SessionManager) erreure lors de la verificatino de la session "
										+ sessionId + " - " + caught);
							}
						});
			}
		} else {
			sessionVerifier.isLoggedIn(loggedIn);
		}
	}
}
