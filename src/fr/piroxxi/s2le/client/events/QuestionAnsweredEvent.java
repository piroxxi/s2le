package fr.piroxxi.s2le.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class QuestionAnsweredEvent extends
		GwtEvent<QuestionAnsweredEventHandler> {

	private final boolean isAnswerRight;

	public QuestionAnsweredEvent(boolean isAnswerRight){
		this.isAnswerRight = isAnswerRight;
	}
	
	public static final GwtEvent.Type<QuestionAnsweredEventHandler> TYPE = new GwtEvent.Type<QuestionAnsweredEventHandler>();

	@Override
	public Type<QuestionAnsweredEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(QuestionAnsweredEventHandler handler) {
		handler.questionAnswered(this);
	}

	public boolean isAnswerRight() {
		return isAnswerRight;
	}

}