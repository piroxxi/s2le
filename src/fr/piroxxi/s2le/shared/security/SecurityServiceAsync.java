package fr.piroxxi.s2le.shared.security;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SecurityServiceAsync {

	void login(String name, String password, AsyncCallback<String> callback);

	void verifySession(String sessionId, AsyncCallback<Boolean> callback);

}
