package fr.piroxxi.s2le.client.test;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.places.TestPlace;

public class TestRunningActivity extends AbstractActivity implements
		TestRunningView.Delegate {

	private final ClientFactory clientFactory;
	private final TestRunningView view;

	public TestRunningActivity(ClientFactory clientFactory, TestPlace testPlace) {
		this.clientFactory = clientFactory;

		this.view = clientFactory.getTestRunningView();
		this.view.setDelegate(this);

		// this.clientFactory.getStoreService().nextQuestionFromTest("",
		// testPlace.getTestId(), new AsyncCallback<Question>() {
		//
		// @Override
		// public void onSuccess(Question result) {
		//
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		//
		// }
		// });
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(this.view);
	}
}
