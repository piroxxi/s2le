package fr.piroxxi.s2le.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TestPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<TestPlace> {

		@Override
		public TestPlace getPlace(String token) {
			return new TestPlace(token);
		}

		@Override
		public String getToken(TestPlace place) {
			return place.getTestId();
		}

	}

	private String testId;

	/**
	 * @return the testId
	 */
	public String getTestId() {
		return testId;
	}

	public TestPlace(String testId) {
		this.testId = testId;
	}
}
