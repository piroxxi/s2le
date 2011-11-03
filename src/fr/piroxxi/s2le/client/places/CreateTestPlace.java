package fr.piroxxi.s2le.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class CreateTestPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<CreateTestPlace> {

		@Override
		public CreateTestPlace getPlace(String token) {
			return new CreateTestPlace();
		}

		@Override
		public String getToken(CreateTestPlace place) {
			return "";
		}

	}

}