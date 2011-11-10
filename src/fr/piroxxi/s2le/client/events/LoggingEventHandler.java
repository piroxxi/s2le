package fr.piroxxi.s2le.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface LoggingEventHandler extends EventHandler {
	void loggedIn(LoggedInEvent loggedInEvent);
	
	void loggedOut(LoggedOutEvent loggedOutEvent);
}
