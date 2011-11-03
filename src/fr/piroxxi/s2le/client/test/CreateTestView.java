package fr.piroxxi.s2le.client.test;

import java.util.List;

import fr.piroxxi.s2le.client.ui.View;
import fr.piroxxi.s2le.shared.model.Category;
import fr.piroxxi.s2le.shared.model.Difficulty;

public interface CreateTestView extends View<CreateTestView.Delegate> {

	public interface Delegate extends View.Delegate {
		public void startTest(List<Difficulty> difficulties,
				List<Category> categories, int quantity, boolean chronometree);
	}
}
