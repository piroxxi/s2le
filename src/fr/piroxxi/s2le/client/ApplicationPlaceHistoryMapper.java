package fr.piroxxi.s2le.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import fr.piroxxi.s2le.client.places.CreateTestPlace;
import fr.piroxxi.s2le.client.places.HelloPlace;
import fr.piroxxi.s2le.client.places.ListeQuestionsPlace;

@WithTokenizers({ HelloPlace.Tokenizer.class, ListeQuestionsPlace.Tokenizer.class,
		CreateTestPlace.Tokenizer.class })
public interface ApplicationPlaceHistoryMapper extends PlaceHistoryMapper {
}