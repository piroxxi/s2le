package fr.piroxxi.s2le.server.messages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.piroxxi.s2le.model.messages.Message;

public class MessageBox {
	public static final MessageBox MESSAGE_BOX = new MessageBox();

	/*
	 * Note pour moi même que je retrouverais jamais parceque j'aurais jamais du
	 * la mettre là. ********************* Lorsque l'on envoie un message, il a
	 * un destinataire. Si on veut envoyer le message a plusieurs utilisateurs,
	 * il aura plusieurs destinataires.
	 */
	/*
	 * Map entre les utilisateurs et la présence de nouveaux messages.
	 */
	private Map<String, Boolean> hasNewMail = new HashMap<String, Boolean>();
	private Map<String, List<Message>> userMail = new HashMap<String, List<Message>>();

	private String[] messagesTropNullMaisOnSEnFouCEstPourLaTelevisionFrancaise = new String[] {
			"bonjour et bienvenu dans l'application, ceci est un message pour vous rappeller que vous existez :) !",
			"bonjour à vous jeune padawan, vous êtes plein de force et de vigueure comme je peux le voir! Continuez comme ca!",
			"bah oui hein, je test l'API de messages :p",
			"ceci est un message de test envoyé aléatoirement aux différents utilisateurs de l'application. Ne vous en faites pas si vous le recevez, c'est que ca marche.",
			"Bwhaaaaaaaaaaaaaaaaaaaaaaaaaaa",
			"Bon bah encore un message pour la route ! Parcequ'on aime bien varier les messages, donc on en envoie plein de différents.",
			"C'est que je suis en forme moi ce soir ! je fais plein de messages trop bien!",
			"Des idées de truc a mettre dans les messages? moi j'en ai plus",
			"erreure, cette application n'est pas reliée à la machine à café, vous ne pouvez pas appeller la méthode do_coffee()",
			"erreure, aucune processus de commande de drogue n'est accessible pour votre compte. Vous n'avez pas les droits necessaires.",
			"PiroXXI est l'hommme le plus beau du monde (ou peu s'en faut)",
			"si si, c'est juste des messages pour tester l'application :) :)",
			"c'est même plutôt du bon message quoi :p" };

	public MessageBox() {
		System.out.println("Creation de la messageBox");
		/*
		 * Cette méthode lance une boucle, qui s'occupe régulièrement d'ajouter
		 * des messages dans les differentes boites au lettres. :p feune hein?
		 */
		Thread simpleThread = new Thread() {

			public void run() {
				while (true) {
					System.out.println("\t\t\tnouvelle boucle! ("
							+ userMail.keySet() + ")");
					try {
						// sleep 20sec
						Thread.sleep(30 * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (String user : userMail.keySet()) {
						sendMessage(
								user,
								new Message(
										messagesTropNullMaisOnSEnFouCEstPourLaTelevisionFrancaise[(int) (Math
												.random() * messagesTropNullMaisOnSEnFouCEstPourLaTelevisionFrancaise.length)],
										"message de test de l'api de messaging."));
					}
				}
			}
		};
		simpleThread.start();
	}

	public void sendMessage(String user, Message message) {
		System.out.println("\n------------\n\t=> l'utilisateur " + user
				+ " a recu le message " + message + " (" + message.getTitle()
				+ ")\n");

		/*
		 * plus, tard, on vera si ca vaut pas le coup de mettre un chtit coup de
		 * storage dans ce bazard :/ :p :)
		 */
		List<Message> messages = userMail.get(user);
		if (messages == null) {
			messages = new ArrayList<Message>();
			userMail.put(user, messages);
		}
		messages.add(message);

		if (!message.isReaden()) {
			hasNewMail.remove(user);
			hasNewMail.put(user, true);
		}
	}

	public boolean hasNewMessage(String user) {
		Boolean bol = hasNewMail.get(user);
		if (bol == null) {
			return false;
		}
		return bol;
	}

	public Message[] getMessages(String user) {
		List<Message> messages = userMail.get(user);
		if (messages == null) {
			return new Message[0];
		}
		Message[] mt = new Message[messages.size()];
		for (int i = 0; i < mt.length; i++) {
			mt[i] = messages.get(i);
		}
		return mt;
	}
}
