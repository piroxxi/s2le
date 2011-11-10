package fr.piroxxi.s2le.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import fr.piroxxi.s2le.client.hello.HelloActivity;
import fr.piroxxi.s2le.client.places.CreateTestPlace;
import fr.piroxxi.s2le.client.places.ErrorPlace;
import fr.piroxxi.s2le.client.places.HelloPlace;
import fr.piroxxi.s2le.client.places.ListeQuestionsPlace;
import fr.piroxxi.s2le.client.places.TestPlace;
import fr.piroxxi.s2le.client.test.CreateTestActivity;
import fr.piroxxi.s2le.client.test.TestRunningActivity;
import fr.piroxxi.s2le.client.test.liste.ListeQuestionsActivity;
import fr.piroxxi.s2le.client.ui.error.ErrorActivity;

public class ApplicationActivityMapper implements ActivityMapper {
	private ClientFactory clientFactory;

	public ApplicationActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		
		if (place instanceof HelloPlace) {
			return new HelloActivity(clientFactory);
			
		} else if (place instanceof CreateTestPlace) {
			return new CreateTestActivity(clientFactory);
			
		} else if (place instanceof TestPlace) {
			return new TestRunningActivity(clientFactory, (TestPlace) place);
			
		} else if (place instanceof ListeQuestionsPlace) {
			return new ListeQuestionsActivity(clientFactory);
			
		} else if (place instanceof ErrorPlace) {
			return new ErrorActivity(clientFactory, (ErrorPlace) place);
		}
		return new ErrorActivity(clientFactory, new ErrorPlace("la place "
				+ place + " est introuvable"));
	}
}