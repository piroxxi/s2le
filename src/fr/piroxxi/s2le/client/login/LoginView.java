package fr.piroxxi.s2le.client.login;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

@ImplementedBy(LoginViewImpl.class)
public interface LoginView extends IsWidget {
	void setConnectedUser(String userName);

	void setDelegate(Delegate delegate);

	/**
	 * Delegate used by the View to communicate with the Activity using the
	 * view.
	 */
	public interface Delegate {
		/**
		 * Login
		 * 
		 * @param userName
		 * @param password
		 */
		void login(String userName, String password);

		/**
		 * Logout.
		 */
		void logout();
	}
}
