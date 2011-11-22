package fr.piroxxi.s2le.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class UserStatistiquesPlace extends Place {

	public static class Tokenizer implements
			PlaceTokenizer<UserStatistiquesPlace> {

		@Override
		public UserStatistiquesPlace getPlace(String token) {
			return new UserStatistiquesPlace();
		}

		@Override
		public String getToken(UserStatistiquesPlace place) {
			return "";
		}

	}

}