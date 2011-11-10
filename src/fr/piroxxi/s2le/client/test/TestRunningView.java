package fr.piroxxi.s2le.client.test;

import com.google.gwt.user.client.ui.SimplePanel;

import fr.piroxxi.s2le.client.test.questions.QuestionPanel;
import fr.piroxxi.s2le.client.ui.View;

public interface TestRunningView extends View<TestRunningView.Delegate> {
	public interface Delegate extends View.Delegate, QuestionPanel.Delegate{

	}

	SimplePanel getMainPanel();
	
	void setTest(String testName, String testAvancement);
}
