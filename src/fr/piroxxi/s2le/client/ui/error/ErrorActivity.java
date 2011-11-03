package fr.piroxxi.s2le.client.ui.error;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Label;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.places.ErrorPlace;

public class ErrorActivity extends AbstractActivity implements
		ErrorView.Delegate {

	@SuppressWarnings("unused")
	private final ClientFactory factory;
	private final ErrorPlace place;

	public ErrorActivity(ClientFactory factory, ErrorPlace place) {
		this.factory = factory;
		this.place = place;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(new Label("Vous avez eu un erreure "
				+ place.getMessage()));
	}
}
