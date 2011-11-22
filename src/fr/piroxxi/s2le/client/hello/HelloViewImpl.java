package fr.piroxxi.s2le.client.hello;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.client.ui.ImagePopup;

public class HelloViewImpl extends Composite implements HelloView {
	interface MyUiBinder extends UiBinder<Widget, HelloViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	public HelloViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	private Delegate delegate;

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@UiHandler("creerCompte")
	public void logout(ClickEvent event) {
		if (delegate != null) {
			delegate.creerCompte();
		}
	}

	@UiHandler({ "image1", "image2" })
	public void onClickImage1(ClickEvent clickEvent) {
		Image img = (Image) clickEvent.getSource();
		new ImagePopup(img.getUrl());
	}
}
