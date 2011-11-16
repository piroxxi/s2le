package fr.piroxxi.s2le.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EditNewQuestionPlace extends Place {

	public static class Tokenizer implements
			PlaceTokenizer<EditNewQuestionPlace> {

		@Override
		public EditNewQuestionPlace getPlace(String token) {
			return new EditNewQuestionPlace(token);
		}

		@Override
		public String getToken(EditNewQuestionPlace place) {
			return place.getQuestionId();
		}

	}

	private final String questionId;

	public String getQuestionId() {
		return this.questionId;
	}

	public EditNewQuestionPlace(String questionId) {
		this.questionId = questionId;
	}
}