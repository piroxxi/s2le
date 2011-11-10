package fr.piroxxi.s2le.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ResultTestPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<ResultTestPlace> {

		@Override
		public ResultTestPlace getPlace(String token) {
			return new ResultTestPlace(token);
		}

		@Override
		public String getToken(ResultTestPlace place) {
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

	public ResultTestPlace(String testId) {
		this.testId = testId;
	}
}
