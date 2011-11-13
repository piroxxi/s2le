package fr.piroxxi.s2le.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface QuestionAnsweredEventHandler extends EventHandler {
	void questionAnswered(QuestionAnsweredEvent questionAnsweredEvent);
}
