package fr.piroxxi.s2le.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ErrorPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<ErrorPlace> {

		@Override
		public ErrorPlace getPlace(String token) {
			return new ErrorPlace(token);
		}

		@Override
		public String getToken(ErrorPlace place) {
			return place.getMessage();
		}

	}

	private String message;

	public ErrorPlace(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
