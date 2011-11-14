package fr.piroxxi.s2le.client.asside.advise;

import fr.piroxxi.s2le.client.ui.View;
import fr.piroxxi.s2le.model.Advise;

public interface AdvisorView extends View<AdvisorView.Delegate> {

	void showAdvise(Advise result);

	public interface Delegate extends View.Delegate {
		void showAdvise();
	}
}
