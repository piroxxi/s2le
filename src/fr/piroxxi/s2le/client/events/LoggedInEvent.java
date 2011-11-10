package fr.piroxxi.s2le.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class LoggedInEvent extends GwtEvent<LoggingEventHandler> {

	public static final GwtEvent.Type<LoggingEventHandler> TYPE = new GwtEvent.Type<LoggingEventHandler>();

	@Override
	public Type<LoggingEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoggingEventHandler handler) {
		handler.loggedIn(this);
	}

}
