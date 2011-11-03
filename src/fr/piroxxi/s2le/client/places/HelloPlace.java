package fr.piroxxi.s2le.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class HelloPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<HelloPlace> {

		@Override
		public HelloPlace getPlace(String token) {
			return new HelloPlace();
		}

		@Override
		public String getToken(HelloPlace place) {
			return "";
		}

	}

}
