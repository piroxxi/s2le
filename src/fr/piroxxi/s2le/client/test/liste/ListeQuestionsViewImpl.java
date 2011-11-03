package fr.piroxxi.s2le.client.test.liste;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ListeQuestionsViewImpl extends Composite implements ListeQuestionsView {
	interface MyUiBinder extends UiBinder<Widget, ListeQuestionsViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@SuppressWarnings("unused")
	private Delegate delegate;

	public ListeQuestionsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}
}
