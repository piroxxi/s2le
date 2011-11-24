package fr.piroxxi.s2le.client.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.model.messages.Message;

public class MessageViewImpl extends Composite implements MessageView {
	interface MyStyle extends CssResource {
		String number();

		String emptyNumber();

		String message();

		String panel();

		String close();

		String croix();
	}

	interface MyUiBinder extends UiBinder<Widget, MessageViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@SuppressWarnings("unused")
	private Delegate delegate;

	@UiField
	MyStyle style;

	@UiField
	Button nbUnreadMessages;

	@UiField
	DisclosurePanel messages;

	@UiField
	VerticalPanel messagesHP;

	@UiField
	DisclosurePanel tempMessage;

	@UiField
	Label tempMessageContent;

	private int unreadCount;

	public MessageViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));

		messages.setAnimationEnabled(true);
		tempMessage.setAnimationEnabled(true);
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showMessage(Message message) {
		messagesHP.add(new Label(message.getTitle()));

		tempMessage.setVisible(true);
		tempMessageContent.setText(message.getTitle());
		tempMessage.setOpen(true);
		Timer timer = new Timer() {
			@Override
			public void run() {
				if (tempMessage.isOpen()) {
					tempMessage.setOpen(false);
					this.schedule(200);
				} else {
					tempMessage.setVisible(false);
				}
			}
		};
		timer.schedule(3000);
	}

	@Override
	public void setUnreadMessagesCount(int unreadCount) {
		this.unreadCount = unreadCount;
		nbUnreadMessages.setText(unreadCount + "");
		if (unreadCount == 0) {
			nbUnreadMessages.setStyleName(style.emptyNumber());
		} else {
			nbUnreadMessages.setStyleName(style.number());
		}
	}

	@UiHandler("nbUnreadMessages")
	public void onShowMessages(ClickEvent clickEvent) {
		if (messages.isVisible()) {
			onHideMessages(null);
		} else {
			if (unreadCount > 0) {
				messages.setVisible(true);
				messages.setOpen(true);
			}
		}
	}

	@UiHandler("close")
	public void onHideMessages(ClickEvent clickEvent) {
		messages.setOpen(false);
		Timer timer = new Timer() {
			@Override
			public void run() {
				messages.setVisible(false);
			}
		};
		timer.schedule(200);
	}

	@Override
	public void removeMessages() {
		while (messagesHP.getWidgetCount() != 0) {
			messagesHP.remove(0);
		}
		setUnreadMessagesCount(0);
	}
}
