package fr.piroxxi.s2le.client.login;

import com.google.inject.ImplementedBy;

import fr.piroxxi.s2le.client.ui.View;

@ImplementedBy(LoginViewImpl.class)
public interface LoginView extends View<LoginView.Delegate> {
	void setConnectedUser(String userName);

	/**
	 * Delegate used by the View to communicate with the Activity using the
	 * view.
	 */
	public interface Delegate extends View.Delegate{
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
