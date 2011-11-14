package fr.piroxxi.s2le.client.test.creation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CreateQuestionViewImpl extends Composite implements
		CreateQuestionView {
	interface MyUiBinder extends UiBinder<Widget, CreateQuestionViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@SuppressWarnings("unused")
	private Delegate delegate;

	public CreateQuestionViewImpl(){
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

}
