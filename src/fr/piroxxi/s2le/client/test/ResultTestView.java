package fr.piroxxi.s2le.client.test;

import fr.piroxxi.s2le.client.ui.View;

public interface ResultTestView extends View<ResultTestView.Delegate> {

	void setResults(String results);

	public interface Delegate extends View.Delegate {
		public void startNewTest();
	}
}
