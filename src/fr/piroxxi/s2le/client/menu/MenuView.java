package fr.piroxxi.s2le.client.menu;

import fr.piroxxi.s2le.client.ui.View;

public interface MenuView extends View<MenuView.Delegate> {

	public interface Delegate extends View.Delegate {

		void showQuestions();

		void startTest();

		void showStatistiques();

	}
}
