package fr.piroxxi.s2le.client.hello;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.places.CreateAccountPlace;

public class HelloActivity extends AbstractActivity implements
		HelloView.Delegate {

	private final ClientFactory factory;
	private HelloView view;

	public HelloActivity(ClientFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.view = factory.getHelloView();
		panel.setWidget(view);
		view.setDelegate(this);
	}

	@Override
	public void creerCompte() {
		this.factory.getPlaceController().goTo(new CreateAccountPlace());
	}
}
