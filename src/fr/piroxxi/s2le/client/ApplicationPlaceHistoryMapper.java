package fr.piroxxi.s2le.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import fr.piroxxi.s2le.client.places.EditNewQuestionPlace;
import fr.piroxxi.s2le.client.places.StartQuestionCreationPlace;
import fr.piroxxi.s2le.client.places.CreateTestPlace;
import fr.piroxxi.s2le.client.places.EditQuestionPlace;
import fr.piroxxi.s2le.client.places.ErrorPlace;
import fr.piroxxi.s2le.client.places.HelloPlace;
import fr.piroxxi.s2le.client.places.ListeQuestionsPlace;
import fr.piroxxi.s2le.client.places.ResultTestPlace;
import fr.piroxxi.s2le.client.places.TestPlace;

@WithTokenizers({ HelloPlace.Tokenizer.class,
		ListeQuestionsPlace.Tokenizer.class, CreateTestPlace.Tokenizer.class,
		TestPlace.Tokenizer.class, ResultTestPlace.Tokenizer.class,
		StartQuestionCreationPlace.Tokenizer.class,
		EditQuestionPlace.Tokenizer.class, ErrorPlace.Tokenizer.class,
		EditNewQuestionPlace.Tokenizer.class })
public interface ApplicationPlaceHistoryMapper extends PlaceHistoryMapper {
}