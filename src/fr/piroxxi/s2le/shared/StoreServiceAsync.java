package fr.piroxxi.s2le.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;
import fr.piroxxi.s2le.model.Test;
import fr.piroxxi.s2le.model.User;

public interface StoreServiceAsync {

	void hasAnswered(String session, String testId, boolean goodAnswer,
			AsyncCallback<Boolean> callback);

	void getCategories(AsyncCallback<Category[]> callback);

	void getTest(String testId, AsyncCallback<Test> callback);

	void createTest(String session, List<Difficulty> difficulties,
			List<Category> categories, int quantity, boolean chronometree,
			AsyncCallback<String> callback);

	void getUser(String session, String userId, AsyncCallback<User> callback);

}
