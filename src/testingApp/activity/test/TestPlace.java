package testingApp.activity.test;

import fr.piroxxi.s2le.model.Test;
import testingApp.ui.placeControler.Place;

public class TestPlace extends Place {
	private Test test;

	public TestPlace(Test test) {
		this.test = test;
	}

	public Test getTest() {
		return this.test;
	}
}
