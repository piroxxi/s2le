package fr.piroxxi.s2le.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.piroxxi.s2le.model.Advise;
import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;
import fr.piroxxi.s2le.model.Test;
import fr.piroxxi.s2le.model.User;
import fr.piroxxi.s2le.model.messages.Message;
import fr.piroxxi.s2le.model.question.Question;
import fr.piroxxi.s2le.model.question.QuestionType;

public interface StoreServiceAsync {

	void hasAnswered(String session, String testId, boolean goodAnswer,
			AsyncCallback<Boolean> callback);

	void getCategories(AsyncCallback<Category[]> callback);

	void getTest(String testId, AsyncCallback<Test> callback);

	void createTest(String session, List<Difficulty> difficulties,
			List<Category> categories, int quantity, boolean chronometree,
			AsyncCallback<String> callback);

	void getUser(String session, String userId, AsyncCallback<User> callback);

	void getRandomAdvise(String session, AsyncCallback<Advise> callback);

	void listeQuestions(String session, AsyncCallback<Question[]> callback);

	void createQuestion(String session, QuestionType questionType,
			AsyncCallback<String> callback);

	void createAccount(String nom, String email, String password,
			AsyncCallback<String> callback);

	void sendMessage(String session, Message message,
			AsyncCallback<Void> callback);

	void hasNewMessage(String session, AsyncCallback<Boolean> callback);

	void getMessages(String session, AsyncCallback<Message[]> callback);
}
