package fr.piroxxi.s2le.client.user.stats;

import fr.piroxxi.s2le.client.ui.View;
import fr.piroxxi.s2le.model.User;

public interface UserStatistiquesView extends
		View<UserStatistiquesView.Delegate> {

	public void hideErrorPanel();

	public void showErrorPanel(String string);

	public void showUserStats(User user);

	public interface Delegate extends View.Delegate {
	}

}
