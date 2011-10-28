package testingApp.ui.placeControler;

import javax.swing.JPanel;

import testingApp.RefreshingEvent;
import testingApp.activity.hello.HelloActivity;
import testingApp.activity.test.TestConfigurationActivity;
import testingApp.activity.test.TestExecutionActivity;
import testingApp.activity.test.TestPlace;
import testingApp.ui.Activity;
import testingApp.ui.eventBus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import fr.piroxxi.s2le.server.model.Test;

@Singleton
public class PlaceControlerImpl implements PlaceControler {

	private final EventBus eventBus;
	private JPanel panel;
	private final Provider<TestConfigurationActivity> testConfigurationActivityProvider;
	private final Provider<TestExecutionActivity> testExecutionActivityProvider;
	private final HelloActivity helloActivity;

	@Inject
	public PlaceControlerImpl(EventBus eventBus, HelloActivity helloActivity,
			Provider<TestConfigurationActivity> testActivityProvider,
			Provider<TestExecutionActivity> testExecutionActivityProvider) {
		this.eventBus = eventBus;
		this.helloActivity = helloActivity;
		this.testConfigurationActivityProvider = testActivityProvider;
		this.testExecutionActivityProvider = testExecutionActivityProvider;
	}

	@Override
	public void initialize(JPanel panel) {
		this.panel = panel;
	}

	@Override
	public void goTo(Place place) {
		System.out.println("demande de déplacement sur la place " + place);

		Activity activity = getActivity(place);
		if (activity != null) {
			panel.removeAll();
			activity.startActivity(panel);
			eventBus.publishEvent(RefreshingEvent.class, new RefreshingEvent());
		}
	}

	private Activity getActivity(Place place) {

		if (place instanceof TestPlace) {
			Test test = ((TestPlace) place).getTest();
			if (test == null) {
				return testConfigurationActivityProvider.get();
			} else {
				TestExecutionActivity activity = testExecutionActivityProvider
						.get();
				activity.setTest(test);
				return activity;
			}
		}

		return helloActivity;
	}

}
