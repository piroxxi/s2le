package fr.piroxxi.s2le.client.test;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.places.CreateTestPlace;
import fr.piroxxi.s2le.client.places.ResultTestPlace;

public class ResultTestActivity extends AbstractActivity implements
		ResultTestView.Delegate {

	private final ClientFactory clientFactory;
	private ResultTestView view;

	public ResultTestActivity(ClientFactory clientFactory,
			ResultTestPlace resultTestPlace) {
		this.clientFactory = clientFactory;
		this.view = clientFactory.getResultTestView();
		this.view.setDelegate(this);

		this.view.setResults(resultTestPlace.getTestResults());
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

	@Override
	public void startNewTest() {
		this.clientFactory.getPlaceController().goTo(new CreateTestPlace());
	}

}
