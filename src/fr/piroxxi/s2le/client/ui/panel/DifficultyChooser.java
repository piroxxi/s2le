package fr.piroxxi.s2le.client.ui.panel;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.model.Difficulty;

public class DifficultyChooser extends Composite {
	interface MyUiBinder extends UiBinder<Widget, DifficultyChooser> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	public DifficultyChooser() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public List<Difficulty> getSelectedDifficulties() {
		return new ArrayList<Difficulty>();
	}
}
