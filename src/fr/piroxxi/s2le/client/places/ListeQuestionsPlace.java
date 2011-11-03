package fr.piroxxi.s2le.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListeQuestionsPlace extends Place{

    public static class Tokenizer implements PlaceTokenizer<ListeQuestionsPlace> {

		@Override
		public ListeQuestionsPlace getPlace(String token) {
			return new ListeQuestionsPlace();
		}

		@Override
		public String getToken(ListeQuestionsPlace place) {
			return "";
		}

    }

}
