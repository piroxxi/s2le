package fr.piroxxi.s2le.client.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ResultTestViewImpl extends Composite implements ResultTestView{
	interface MyUiBinder extends UiBinder<Widget, ResultTestViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	private Delegate delegate;

	@UiField
	Label result;
	
	public ResultTestViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void setResults(String results) {
		result.setText(results);
	}
	
	@UiHandler("nextTest")
	public void nextTest(ClickEvent event) {
		if (delegate != null) {
			delegate.startNewTest();
		}
	}
}
