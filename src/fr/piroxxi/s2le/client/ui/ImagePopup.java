package fr.piroxxi.s2le.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class ImagePopup {
	interface MyUiBinder extends UiBinder<Widget, ImagePopup> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	Image image;

	@UiField
	PopupPanel popup;

	public ImagePopup(String url) {
		uiBinder.createAndBindUi(this);
		image.setUrl(url);
		popup.center();
	}

	@UiHandler("close")
	public void onClose(ClickEvent clickEvent) {
		popup.hide();
	}
}
