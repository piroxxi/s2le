package fr.piroxxi.s2le.client.messages;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.events.LoggedInEvent;
import fr.piroxxi.s2le.client.events.LoggedOutEvent;
import fr.piroxxi.s2le.client.events.LoggingEventHandler;
import fr.piroxxi.s2le.client.events.NewMessageEvent;
import fr.piroxxi.s2le.client.events.NewMessageHandler;
import fr.piroxxi.s2le.client.ui.SessionManager.SessionVerifier;

public class MessageActivity extends AbstractActivity implements
		MessageView.Delegate, NewMessageHandler, LoggingEventHandler {

	private final ClientFactory clientFactory;
	private MessageView view;

	public MessageActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.view = this.clientFactory.getMessageView();
		this.view.setDelegate(this);

		this.clientFactory.getSessionManager().isLoggedIn(
				new SessionVerifier() {

					@Override
					public void isLoggedIn(boolean loggedIn) {
						if (loggedIn) {
							MessageActivity.this.view.setVisible(true);
						} else {
							MessageActivity.this.view.setVisible(false);
						}
					}
				});

		this.clientFactory.getEventBus().addHandler(NewMessageEvent.TYPE, this);
		this.clientFactory.getEventBus().addHandler(LoggedInEvent.TYPE, this);
		this.clientFactory.getEventBus().addHandler(LoggedOutEvent.TYPE, this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

	@Override
	public void goTo(Place place) {
		this.clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void onNewMessage(NewMessageEvent newMessageEvent) {
		this.view.showMessage(this.clientFactory.getMessageBox().getMessage(
				newMessageEvent.getMessageId()));
		this.view.setUnreadMessagesCount(this.clientFactory.getMessageBox()
				.getUnreadCount());
	}

	@Override
	public void loggedIn(LoggedInEvent loggedInEvent) {
		this.view.setVisible(true);
	}

	@Override
	public void loggedOut(LoggedOutEvent loggedOutEvent) {
		this.view.setVisible(false);
		this.view.removeMessages();
	}

}
