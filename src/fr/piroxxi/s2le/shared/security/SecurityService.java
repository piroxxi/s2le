package fr.piroxxi.s2le.shared.security;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("security")
public interface SecurityService extends RemoteService {

	/**
	 * 
	 * @param name
	 *            : user login
	 * @param password
	 *            : user's password (must be encrypted on client side)
	 * @return the session ID (should be stored in a cookie to stay logged in)
	 * @throws IllegalArgumentException
	 */
	String login(String name, String password) throws IllegalArgumentException;

	/**
	 * Demande au serveur de verifier que la session en cours est bien valide.
	 * Une telle demande rafraichie le timeout coté serveur.
	 * 
	 * @param sessionId
	 *            identifiant de la session à verifier.
	 * @return true si la session est en cours de validité, false sinon.
	 * @throws IllegalArgumentException
	 */
	Boolean verifySession(String sessionId) throws IllegalArgumentException;
}
