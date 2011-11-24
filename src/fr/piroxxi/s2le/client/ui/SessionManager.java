package fr.piroxxi.s2le.client.ui;

import java.util.Date;

import com.google.gwt.user.client.Cookies;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.events.LoggedInEvent;
import fr.piroxxi.s2le.client.events.LoggedOutEvent;

public class SessionManager {
	public interface SessionVerifier {
		public void isLoggedIn(boolean loggedIn);
	}

	private static final Long TEN_MINUTES = new Long(1000 * 60 * 10);

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

	/**
	 * ATTENTION! Cette fonction doit être appellée apres la fonction
	 * isLoggerId().
	 * 
	 * @return
	 */
	public String getSessionId() {
		return sessionId;
	}

	public void setLoggedIn(String userName, String sessionId) {
		this.userName = userName;
		Cookies.setCookie("userName", userName, new Date(new Date().getTime()
				+ TEN_MINUTES));
		this.sessionId = sessionId;
		Cookies.setCookie("sessionId", sessionId, new Date(new Date().getTime()
				+ TEN_MINUTES));
		this.loggedIn = true;
		this.clientFactory.getEventBus().fireEvent(new LoggedInEvent());
	}

	public void setLoggedOut() {
		this.userName = null;
		Cookies.removeCookie("userName");
		this.sessionId = null;
		Cookies.removeCookie("sessionId");
		this.loggedIn = false;
		this.clientFactory.getEventBus().fireEvent(new LoggedOutEvent());
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
			final String sessionId = Cookies.getCookie("sessionId");
			final String userName = Cookies.getCookie("userName");

			if (sessionId == null) {
				sessionVerifier.isLoggedIn(false);
				setLoggedOut();
			} else {
				clientFactory
						.getSecurityService()
						.verifySession(
								sessionId,
								new OperationCallback<Boolean>(
										this.clientFactory,
										"(SessionManager) Erreure lors de la verification de la session") {

									@Override
									public void onSuccess(Boolean result) {
										if (result) {
											setLoggedIn(userName, sessionId);
										} else {
											setLoggedOut();
										}
										sessionVerifier.isLoggedIn(result);
									}
								});
			}
			isApplicationStarting = false;
		} else {
			sessionVerifier.isLoggedIn(loggedIn);
		}
	}
}
