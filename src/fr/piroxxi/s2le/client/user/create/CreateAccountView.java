package fr.piroxxi.s2le.client.user.create;

import fr.piroxxi.s2le.client.ui.View;

public interface CreateAccountView extends View<CreateAccountView.Delegate> {

	public interface Delegate extends View.Delegate {
		public void createAccount(String name, String password, String email);
	}
}
