package fr.piroxxi.s2le.client.asside.advise;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.model.Advise;

public class AdvisorViewImpl extends Composite implements AdvisorView {
	interface MyUiBinder extends UiBinder<Widget, AdvisorViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	private Delegate delegate;

	@UiField
	Label advise;

	public AdvisorViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showAdvise(Advise result) {
		advise.setText(result.getShortAdvise());
	}

	@UiHandler("enSavoirPlus")
	public void enSavoirPlus(ClickEvent clickEvent) {
		if (delegate != null) {
			delegate.showAdvise();
		}
	}
}
