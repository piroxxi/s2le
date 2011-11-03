package fr.piroxxi.s2le.client.asside;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.shared.model.UserStats;

public class AssideActivity extends AbstractActivity implements
		AssideView.Delegate {

	private AssideView view;
	private final ClientFactory factory;

	public AssideActivity(ClientFactory factory) {
		this.factory = factory;
		/**
		 * TODO(rpo) : crée un nouveau EventLIstener, qui voit quand on se log
		 * :) .
		 */
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.view = factory.getAssideView();
		panel.setWidget(view);
		view.setDelegate(this);
	}

	@Override
	public void viewUser(UserStats user) {
		Window.alert("(AssideActivity)TODO: Visit the user " + user);
	}

	@Override
	public void createAccount() {
		Window.alert("(AssideActivity)TODO: creation d'un nouveau compte");
	}

}
