package fr.piroxxi.s2le.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class StartQuestionCreationPlace extends Place {

	public static class Tokenizer implements
			PlaceTokenizer<StartQuestionCreationPlace> {

		@Override
		public StartQuestionCreationPlace getPlace(String token) {
			return new StartQuestionCreationPlace();
		}

		@Override
		public String getToken(StartQuestionCreationPlace place) {
			return "";
		}

	}

}