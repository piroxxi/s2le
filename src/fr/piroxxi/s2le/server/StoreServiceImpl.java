package fr.piroxxi.s2le.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.piroxxi.s2le.model.Advise;
import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;
import fr.piroxxi.s2le.model.Test;
import fr.piroxxi.s2le.model.User;
import fr.piroxxi.s2le.model.messages.Message;
import fr.piroxxi.s2le.model.question.MultiChoicesQuestion;
import fr.piroxxi.s2le.model.question.Question;
import fr.piroxxi.s2le.model.question.QuestionType;
import fr.piroxxi.s2le.model.question.SimpleQuestion;
import fr.piroxxi.s2le.model.question.Translation;
import fr.piroxxi.s2le.model.question.YesNoQuestion;
import fr.piroxxi.s2le.server.injector.ServerModule;
import fr.piroxxi.s2le.server.messages.MessageBox;
import fr.piroxxi.s2le.server.security.SessionManager;
import fr.piroxxi.s2le.shared.StoreService;
import fr.piroxxi.s2le.shared.security.LogginException;
import fr.piroxxi.s2le.storage.api.Filter;
import fr.piroxxi.s2le.storage.api.Query;
import fr.piroxxi.s2le.storage.api.Storage;
import fr.piroxxi.s2le.storage.fake.StorageRemplisseur;

@SuppressWarnings("serial")
public class StoreServiceImpl extends RemoteServiceServlet implements
		StoreService {

	private Storage storage;
	private SessionManager sessionManager;
	private Map<String, Test> currentTests = new HashMap<String, Test>();
	private MessageBox messageBox = MessageBox.MESSAGE_BOX;

	public StoreServiceImpl() {
		Injector injector = Guice.createInjector(new ServerModule());
		this.storage = injector.getInstance(Storage.class);
		this.sessionManager = SessionManager.sessionManager; // TODO mettre de
																// l'injection
		StorageRemplisseur.remplissageStorage(storage);
	}

	public Category[] getCategories() {
		List<Category> liste = storage.createQuery(Category.class)
				.retrieveAsList();
		Category[] ret = new Category[liste.size()];
		for (int i = 0; i < liste.size(); i++) {
			ret[i] = liste.get(i);
		}

		return ret;
	}

	@Override
	public String createTest(String session,
			final List<Difficulty> difficulties,
			final List<Category> categories, int quantity, boolean chronometree) {
		System.out.println("appel à createTest(" + session + ", "
				+ difficulties + ", " + categories + ", " + quantity + ", "
				+ chronometree + ")");

		if (sessionManager.isValide(session)) {
			// On comptabilise (un nouveau test crée)
			String userId = sessionManager.getUserId(session);
			User user = this.storage.getEntity(User.class, userId);
			user.startATest();
			this.storage.store(User.class, user);
		}

		System.out.println("creation du nouvel objet test");

		Query<Question> query = storage.createQuery(Question.class);
		System.out.println("\ncreation de la query");
		if (!difficulties.isEmpty()) {
			query.addFilter(new Filter<Question>() {

				@Override
				public boolean filter(Question entitie) {
					System.out.println("la question " + entitie
							+ " a une difficulté " + entitie.getDifficulty());
					return difficulties.contains(entitie.getDifficulty());
				}
			});
		}
		if (!categories.isEmpty()) {
			query.addFilter(new Filter<Question>() {
				@Override
				public boolean filter(Question entitie) {
					for (Category cat : categories) {
						if (entitie.getCategory().getId().equals(cat.getId())) {
							return true;
						}
					}
					return false;
					// TODO(raphi) : si on a mis la catégorie parente, les
					// enfants doivent aussi être pris (ex: on choisi 'fruits et
					// legumes', il faut récupérée les question qui traitent de
					// 'fuits', et celle qui traitents de 'légumes');
				}
			});
		}

		System.out.println("\nExecution de la query.");
		List<Question> questions = query.retrieveAsList();
		if (questions.isEmpty()) {
			return null;
		}

		System.out.println("\nOn a récupéré " + questions.size()
				+ " questions (" + questions + ")");
		while (questions.size() > quantity) {
			questions.remove((int) (Math.random() * questions.size()));
		}
		System.out.println("on a la liste " + questions);
		Collections.shuffle(questions);
		System.out.println("qui devient " + questions);

		Test test = new Test(questions, chronometree);
		test.setId(UUID.randomUUID().toString());
		currentTests.put(test.getId(), test);

		return test.getId();
	}

	@Override
	public Boolean hasAnswered(String session, String testId, boolean goodAnswer) {
		Test test = currentTests.get(testId);
		if (test != null) {
			boolean hasMoreQuestions = test.hasAnswered(goodAnswer);
			if (sessionManager.isValide(session)) {
				// On comptabilise (un nouveau test crée)
				String userId = sessionManager.getUserId(session);
				User user = this.storage.getEntity(User.class, userId);
				user.hasAnswered(goodAnswer);
				if (!hasMoreQuestions) {
					user.finishedATest();
				}
				this.storage.store(User.class, user);
			}
			return hasMoreQuestions;
		}

		return false;
	}

	@Override
	public Test getTest(String testId) {
		Test test = currentTests.get(testId);
		if (test == null) {
			return null;
		}
		return test;
	}

	@Override
	public User getUser(String session, String userId) throws LogginException {
		System.out.println("tentative d'appel a getUser (" + session + ", "
				+ userId + ")");

		if (!sessionManager.isValide(session)) {
			throw new LogginException("vous n'êtes pas connecté");
		}

		if (sessionManager.getUserId(session).equals(userId)) {
			return this.storage.getEntity(User.class, userId);
		}

		// TODO Test si c'est bien un admin!
		return null;
	}

	@Override
	public Advise getRandomAdvise(String session) {
		List<Advise> advices = storage.createQuery(Advise.class)
				.retrieveAsList();
		return advices.get((int) (Math.random() * advices.size()));
	}

	@Override
	public Question[] listeQuestions(String session) throws LogginException {
		if (!sessionManager.isValide(session)) {
			throw new LogginException("vous n'êtes pas connecté");
		}

		final String userName = sessionManager.getUserId(session);
		Query<Question> query = storage.createQuery(Question.class);
		query.addFilter(new Filter<Question>() {
			@Override
			public boolean filter(Question entitie) {
				return userName.equals(entitie.getCreatorId());
			}
		});
		List<Question> liste = query.retrieveAsList();

		Question[] questions = new Question[liste.size()];
		for (int i = 0; i < liste.size(); i++) {
			questions[i] = liste.get(i);
		}

		return questions;
	}

	@Override
	public String createQuestion(String session, QuestionType questionType)
			throws LogginException {
		if (!sessionManager.isValide(session)) {
			throw new LogginException("vous n'êtes pas connecté");
		}

		Question question = null;
		switch (questionType) {
		case MultiChoiceQuestion:
			question = new MultiChoicesQuestion();
			break;
		case SimpleQuestion:
			question = new SimpleQuestion();
			break;
		case Translation:
			question = new Translation();
			break;
		case YesNoQuestion:
			question = new YesNoQuestion();
			break;
		}

		if (question == null) {
			return null;
		}

		question.setCreatorId(sessionManager.getUserId(session));
		storage.store(Question.class, question);
		return question.getId();
	}

	@Override
	public String createAccount(String nom, String email, String password) {
		// step 1 : verification que personne ne porte ce nom
		User user = storage.getEntity(User.class, nom);
		if (user != null) {
			return "Erreur, ce nom d'utilisateur est déja pris.";
		}
		storage.store(User.class, new User(nom, password, email));
		return null;
	}

	/*
	 * Here, we will probably need something like a MessageBox, on the server
	 * side.
	 */

	@Override
	public void sendMessage(String session, Message message)
			throws LogginException {
		System.out.println("[StoreServiceImpl] - appel à sendMessage("
				+ session + ", " + message + ")");
		if (!sessionManager.isValide(session)) {
			throw new LogginException("vous n'êtes pas connecté");
		}

		messageBox.sendMessage(sessionManager.getUserId(session), message);
	}

	@Override
	public Boolean hasNewMessage(String session) throws LogginException {
		System.out.println("[StoreServiceImpl] - appel à hasNewMessage("
				+ session + ")");
		if (!sessionManager.isValide(session)) {
			throw new LogginException("vous n'êtes pas connecté");
		}

		return messageBox.hasNewMessage(sessionManager.getUserId(session));
	}

	@Override
	public Message[] getMessages(String session) throws LogginException {
		System.out.println("[StoreServiceImpl] - appel à getMessages("
				+ session + ")");
		if (!sessionManager.isValide(session)) {
			throw new LogginException("vous n'êtes pas connecté");
		}

		return messageBox.getMessages(sessionManager.getUserId(session));
	}
}
