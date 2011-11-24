package fr.piroxxi.s2le.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface NewMessageHandler extends EventHandler {
	void onNewMessage(NewMessageEvent newMessageEvent);
}
