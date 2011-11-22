package fr.piroxxi.s2le.client.user.confirm;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.places.CreateAccountConfirmationPlace;

public class CreateAccountConfirmationActivity extends AbstractActivity
		implements CreateAccountConfirmationView.Delegate {

	private final ClientFactory clientFactory;
	private CreateAccountConfirmationView view;

	public CreateAccountConfirmationActivity(ClientFactory clientFactory,
			CreateAccountConfirmationPlace place) {
		this.clientFactory = clientFactory;
		
		this.view = this.clientFactory.getCreateAccountConfirmationView();
		this.view.setDelegate(this);
		this.view.setInfos(place.getUserName(), place.getUserEmail());
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

}
