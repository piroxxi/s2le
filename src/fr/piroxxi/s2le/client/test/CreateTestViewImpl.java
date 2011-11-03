package fr.piroxxi.s2le.client.test;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.shared.model.Category;
import fr.piroxxi.s2le.shared.model.Difficulty;

public class CreateTestViewImpl extends Composite implements CreateTestView {
	interface MyUiBinder extends UiBinder<Widget, CreateTestViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	private Delegate delegate;

	public CreateTestViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@UiHandler("start")
	public void start(ClickEvent event) {
		if (delegate != null) {
			delegate.startTest(new ArrayList<Difficulty>(),
					new ArrayList<Category>(), 10, false);
		}
	}
}
