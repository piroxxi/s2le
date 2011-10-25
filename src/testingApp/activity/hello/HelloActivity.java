package testingApp.activity.hello;

import javax.swing.JPanel;

import testingApp.activity.test.TestPlace;
import testingApp.ui.Activity;
import testingApp.ui.placeControler.PlaceControler;

import com.google.inject.Inject;

public class HelloActivity extends Activity implements HelloView.Delegate {

	private final HelloView view;
	private final PlaceControler placeControler;

	@Inject
	public HelloActivity(PlaceControler placeControler, HelloView view) {
		this.placeControler = placeControler;
		this.view = view;
	}

	@Override
	public void startActivity(JPanel panel) {
		view.setDelegate(this);
		panel.add(view);
	}

	@Override
	public void startTest() {
		placeControler.goTo(new TestPlace(null));
	}

}
