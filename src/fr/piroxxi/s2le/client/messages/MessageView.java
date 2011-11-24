package fr.piroxxi.s2le.client.messages;

import com.google.gwt.place.shared.Place;

import fr.piroxxi.s2le.client.ui.View;
import fr.piroxxi.s2le.model.messages.Message;

public interface MessageView extends View<MessageView.Delegate> {

	public void showMessage(Message message);

	public void setUnreadMessagesCount(int unreadCount);

	public void setVisible(boolean b);

	public void removeMessages();

	public interface Delegate extends View.Delegate {
		/**
		 * Les messages peuvent contenir des places :) .
		 * 
		 * @param place
		 */
		public void goTo(Place place);
	}

}
