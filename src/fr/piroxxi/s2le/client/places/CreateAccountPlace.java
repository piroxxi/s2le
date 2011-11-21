package fr.piroxxi.s2le.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class CreateAccountPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<CreateAccountPlace> {

		@Override
		public CreateAccountPlace getPlace(String token) {
			return new CreateAccountPlace();
		}

		@Override
		public String getToken(CreateAccountPlace place) {
			return "";
		}

	}

}