package fr.piroxxi.s2le.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class CreateQuestionPlace extends Place {

	public static class Tokenizer implements
			PlaceTokenizer<CreateQuestionPlace> {

		@Override
		public CreateQuestionPlace getPlace(String token) {
			return new CreateQuestionPlace();
		}

		@Override
		public String getToken(CreateQuestionPlace place) {
			return "";
		}

	}

}