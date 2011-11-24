package fr.piroxxi.s2le.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class NewMessageEvent extends GwtEvent<NewMessageHandler> {

	public static final GwtEvent.Type<NewMessageHandler> TYPE = new GwtEvent.Type<NewMessageHandler>();
	private final String messageId;

	public NewMessageEvent(String MessageId) {
		messageId = MessageId;
	}

	@Override
	public Type<NewMessageHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(NewMessageHandler handler) {
		handler.onNewMessage(this);
	}

	public String getMessageId() {
		return this.messageId;
	}

}
