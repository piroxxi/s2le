package fr.piroxxi.s2le.client.user.confirm;

import fr.piroxxi.s2le.client.ui.View;

public interface CreateAccountConfirmationView extends
		View<CreateAccountConfirmationView.Delegate> {

	public void setInfos(String nom, String email);

	public interface Delegate extends View.Delegate {
	}
}
