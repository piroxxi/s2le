package fr.piroxxi.s2le.client.hello;

import fr.piroxxi.s2le.client.ui.View;

public interface HelloView extends View<HelloView.Delegate> {

	public interface Delegate extends View.Delegate {
		void creerCompte();
	}

}
