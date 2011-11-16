package fr.piroxxi.s2le.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EditQuestionPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<EditQuestionPlace> {

		@Override
		public EditQuestionPlace getPlace(String token) {
			return new EditQuestionPlace(token);
		}

		@Override
		public String getToken(EditQuestionPlace place) {
			return place.getQuestionId();
		}

	}

	private final String questionId;

	public String getQuestionId() {
		return this.questionId;
	}

	public EditQuestionPlace(String questionId) {
		this.questionId = questionId;
	}
}