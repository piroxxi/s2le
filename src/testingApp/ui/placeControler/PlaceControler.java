package testingApp.ui.placeControler;

import javax.swing.JPanel;

import com.google.inject.ImplementedBy;

@ImplementedBy(PlaceControlerImpl.class)
public interface PlaceControler {
	void initialize(JPanel panel);
	
	void goTo(Place place);
}
