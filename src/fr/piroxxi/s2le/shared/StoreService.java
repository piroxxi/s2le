package fr.piroxxi.s2le.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.piroxxi.s2le.model.Advise;
import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;
import fr.piroxxi.s2le.model.Test;
import fr.piroxxi.s2le.model.User;
import fr.piroxxi.s2le.model.messages.Message;
import fr.piroxxi.s2le.model.question.Question;
import fr.piroxxi.s2le.model.question.QuestionType;
import fr.piroxxi.s2le.shared.security.LogginException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("store")
public interface StoreService extends RemoteService {
	String createTest(String session, List<Difficulty> difficulties,
			List<Category> categories, int quantity, boolean chronometree);

	/*
	 * Methodes relatives aux tests.
	 */
	Test getTest(String testId);

	Boolean hasAnswered(String session, String testId, boolean goodAnswer);

	Category[] getCategories();

	/*
	 * Methodes relatives aux utilisateurs
	 */
	String createAccount(String nom, String email, String password);

	User getUser(String session, String userId) throws LogginException;

	/*
	 * Methodes relatives aux questions.
	 */
	Question[] listeQuestions(String session) throws LogginException;

	String createQuestion(String session, QuestionType questionType)
			throws LogginException;

	/*
	 * Methodes relatives aux conseils.
	 */
	Advise getRandomAdvise(String session);

	/*
	 * Methodes for message.
	 */
	void sendMessage(String session, Message message) throws LogginException;

	Boolean hasNewMessage(String session) throws LogginException;

	Message[] getMessages(String session) throws LogginException;
}
