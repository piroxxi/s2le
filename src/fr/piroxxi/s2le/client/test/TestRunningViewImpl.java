package fr.piroxxi.s2le.client.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class TestRunningViewImpl extends Composite implements TestRunningView {
	interface MyUiBinder extends UiBinder<Widget, TestRunningViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	SimplePanel question;

	@UiField
	Label testName;

	@UiField
	Label testAvancement;

	public TestRunningViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public SimplePanel getMainPanel() {
		return question;
	}

	@Override
	public void setDelegate(Delegate delegate) {
	}

	@Override
	public void setTest(String testName, String testAvancement) {
		this.testName.setText(testName);
		this.testAvancement.setText(testAvancement);
	}
}
