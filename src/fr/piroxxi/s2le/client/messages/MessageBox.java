package fr.piroxxi.s2le.client.messages;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.events.LoggedInEvent;
import fr.piroxxi.s2le.client.events.LoggedOutEvent;
import fr.piroxxi.s2le.client.events.LoggingEventHandler;
import fr.piroxxi.s2le.client.events.NewMessageEvent;
import fr.piroxxi.s2le.client.ui.OperationCallback;
import fr.piroxxi.s2le.client.ui.SessionManager.SessionVerifier;
import fr.piroxxi.s2le.model.messages.Message;

public class MessageBox implements LoggingEventHandler {

	private static final int MSG_REFRESH_RATE = 12 * 1000;

	private List<Message> messages;
	private final ClientFactory clientFactory;
	private Timer timer;

	public MessageBox(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.clientFactory.getEventBus().addHandler(LoggedInEvent.TYPE, this);
		this.clientFactory.getEventBus().addHandler(LoggedOutEvent.TYPE, this);

		messages = new ArrayList<Message>();

		clientFactory.getSessionManager().isLoggedIn(new SessionVerifier() {

			@Override
			public void isLoggedIn(boolean loggedIn) {
				if (loggedIn) {
					GWT.log("[MessageBox] - L'utilisateur est loggé, on lance le timer.");
					timer = new Timer() {

						@Override
						public void run() {
							checkForNewMessages();
						}
					};
					timer.scheduleRepeating(MSG_REFRESH_RATE);
				}
			}
		});
	}

	public void sendMessage(String title, String content) {
		sendMessage(new Message(title, content));
	}

	public void sendMessage(final Message message) {
		messages.add(message);
		/*
		 * La on lance un appel, mais comme on peut très bien utiliser l'API de
		 * messages en mode "non connecté", il faut verifier que l'utilisateur
		 * est loggé... Normalement, cet appel est synchrone.
		 */
		clientFactory.getSessionManager().isLoggedIn(new SessionVerifier() {

			@Override
			public void isLoggedIn(boolean loggedIn) {
				if (loggedIn) {
					GWT.log("[MessageBox] - On a recu le message " + message
							+ " et on le transmet au serveur.");
					clientFactory.getStoreService().sendMessage(
							clientFactory.getSessionManager().getSessionId(),
							message, new OperationCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
								}
							});
				} else {
					GWT.log("[MessageBox] - On a recu le message "
							+ message
							+ " mais malheureusement on est pas loggé. Alors on va attendre que tout soit bon.");
					delayMessageSending(message);
				}
			}
		});

		clientFactory.getEventBus().fireEvent(
				new NewMessageEvent(message.getId()));
	}

	private void checkForNewMessages() {
		clientFactory.getStoreService().hasNewMessage(
				clientFactory.getSessionManager().getSessionId(),
				new OperationCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean hasNewMessages) {
						if (hasNewMessages) {
							GWT.log("[MessageBox] - En allant verifier auprès du serveur, on a apris qu'il y avait des messages que nous n'avions pas encore.");
							updateMessagesContent();
						}
					}

				});
	}

	private void updateMessagesContent() {
		clientFactory.getStoreService().getMessages(
				clientFactory.getSessionManager().getSessionId(),
				new OperationCallback<Message[]>() {

					@Override
					public void onSuccess(Message[] result) {
						for (Message msg : result) {
							boolean weHaveIt = false;
							for (Message m2 : messages) {
								if (msg.getId().equals(m2.getId())) {
									weHaveIt = true;
								}
							}
							if (!weHaveIt) {
								messages.add(msg);
								clientFactory.getEventBus().fireEvent(
										new NewMessageEvent(msg.getId()));
							}
						}

					}
				});
	}

	public int getUnreadCount() {
		int unreaden = 0;
		for (Message msg : messages) {
			if (!msg.isReaden()) {
				unreaden++;
			}
		}
		return unreaden;
	}

	public Message getMessage(String id) {
		for (Message msg : messages) {
			if (msg.getId().equals(id)) {
				return msg;
			}
		}
		return null;
	}

	public List<Message> getUnreadMessages() {
		List<Message> unreadMessages = new ArrayList<Message>();
		for (Message msg : messages) {
			if (!msg.isReaden()) {
				unreadMessages.add(msg);
			}
		}
		return unreadMessages;
	}

	private List<Message> delayedMessages = new ArrayList<Message>();

	private void delayMessageSending(Message message) {
		delayedMessages.add(message);
	}

	@Override
	public void loggedIn(LoggedInEvent loggedInEvent) {
		GWT.log("[MessageBox] - L'utilisateur vient de se logger, on relance le timer qui se charge d'aller chercher les messages.");
		this.timer = new Timer() {

			@Override
			public void run() {
				checkForNewMessages();
			}
		};
		this.timer.scheduleRepeating(MSG_REFRESH_RATE);

		/*
		 * Et on envoie les messages "retardés" au serveur.
		 */
		while (delayedMessages.size() > 0) {
			Message message = delayedMessages.get(0);
			GWT.log("[MessageBox] - Le message "
					+ message
					+ " étant en attente d'être envoyé au serveur, désormais on peut l'envoyer.");
			delayedMessages.remove(0);

			clientFactory.getStoreService().sendMessage(
					clientFactory.getSessionManager().getSessionId(), message,
					new OperationCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
						}
					});
		}
	}

	@Override
	public void loggedOut(LoggedOutEvent loggedOutEvent) {
		if (timer != null) {
			timer.cancel();
		}
		timer = null;
	}
}
