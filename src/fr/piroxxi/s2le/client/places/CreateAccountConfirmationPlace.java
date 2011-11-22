package fr.piroxxi.s2le.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class CreateAccountConfirmationPlace extends Place {

	public static class Tokenizer implements
			PlaceTokenizer<CreateAccountConfirmationPlace> {

		@Override
		public CreateAccountConfirmationPlace getPlace(String token) {
			String[] args = token.split(":", -1);
			return new CreateAccountConfirmationPlace(args[0], args[1]);
		}

		@Override
		public String getToken(CreateAccountConfirmationPlace place) {
			return place.getUserName() + ":" + place.getUserEmail();
		}

	}

	private String name;
	private String email;

	public CreateAccountConfirmationPlace(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getUserName() {
		return this.name;
	}

	public String getUserEmail() {
		return this.email;
	}
}
