package fr.piroxxi.s2le.client.menu;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.places.CreateTestPlace;
import fr.piroxxi.s2le.client.places.ListeQuestionsPlace;
import fr.piroxxi.s2le.client.places.UserStatistiquesPlace;

public class MenuActivity extends AbstractActivity implements MenuView.Delegate {

	private final ClientFactory factory;
	private MenuView view;

	public MenuActivity(ClientFactory factory) {
		this.factory = factory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.view = factory.getMenuView();
		view.setDelegate(this);
		panel.setWidget(view);
	}

	@Override
	public void showQuestions() {
		this.factory.getPlaceController().goTo(new ListeQuestionsPlace());
	}

	@Override
	public void startTest() {
		this.factory.getPlaceController().goTo(new CreateTestPlace());
	}

	@Override
	public void showStatistiques() {
		this.factory.getPlaceController().goTo(new UserStatistiquesPlace());
	}

}
