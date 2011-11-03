package fr.piroxxi.s2le.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MenuViewImpl extends Composite implements MenuView {
	interface MyUiBinder extends UiBinder<Widget, MenuViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	private Delegate delegate;

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	public MenuViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("questions")
	public void questions(ClickEvent event) {
		if (delegate != null) {
			delegate.showQuestions();
		}
	}

	@UiHandler("startTest")
	public void startTest(ClickEvent event) {
		if (delegate != null) {
			delegate.startTest();
		}
	}

	@UiHandler("statistiques")
	public void statistiques(ClickEvent event) {
		if (delegate != null) {
			delegate.showStatistiques();
		}
	}
}
