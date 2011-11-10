package fr.piroxxi.s2le.server.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.inject.Singleton;

@Singleton
public class SessionManager {
	private static final Long TEN_MINUTES = new Long(1000 * 60 * 10);

	private Map<String, String> sessions;
	private Map<String, Date> sessionsTimmer;

	public SessionManager() {
		sessions = new HashMap<String, String>();
		sessionsTimmer = new HashMap<String, Date>();
	}

	/**
	 * Crée une session pour l'utilisateur donné.
	 * 
	 * @param name
	 *            nom de l'utilisateur
	 * @return le numéro de session.
	 */
	public String createSession(String name) {
		String sessionId = UUID.randomUUID().toString();

		sessions.put(sessionId, name);
		sessionsTimmer.put(sessionId, new Date(new Date().getTime()
				+ TEN_MINUTES));

		return sessionId;
	}

	/**
	 * Vérifie si le numéro de session existe.
	 * 
	 * @param sessionId
	 *            numéro de session.
	 * @return vrai si le numéro est valide, faux sinon.
	 */
	public boolean isValide(String sessionId) {
		if (sessions.get(sessionId) == null) {
			return false;
		}

		Date sessionTimout = sessionsTimmer.get(sessionId);
		return sessionTimout.after(new Date());
	}

	/**
	 * Indique si la session est valide, et la renouvelle si elle l'est.
	 * 
	 * @param sessionId
	 *            numéro de session.
	 * @return vrai si le numéro est valide, faux sinon.
	 */
	public boolean renewSessionIfValide(String sessionId) {
		if (sessions.get(sessionId) == null) {
			return false;
		}

		Date sessionTimout = sessionsTimmer.get(sessionId);
		if (sessionTimout.before(new Date())) {
			return false;
		}

		// la session est valide :) on la renouvelle :)
		sessionsTimmer.remove(sessionId);
		sessionsTimmer.put(sessionId, new Date(new Date().getTime()
				+ TEN_MINUTES));
		return true;
	}

	public String getUserId(String sessionId) {
		return sessions.get(sessionId);
	}
}
