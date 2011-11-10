package fr.piroxxi.s2le.client.asside;

import com.google.inject.ImplementedBy;

import fr.piroxxi.s2le.client.ui.View;
import fr.piroxxi.s2le.model.User;

@ImplementedBy(AssideViewImpl.class)
public interface AssideView extends View<AssideView.Delegate> {
	void showUserStats(User user);

	void hideUserStats();

	void setDelegate(Delegate delegate);

	public interface Delegate extends UserStatsPanel.Delegate, View.Delegate {
		void createAccount();
	}

}
