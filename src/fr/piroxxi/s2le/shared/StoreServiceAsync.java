package fr.piroxxi.s2le.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.piroxxi.s2le.server.model.Category;
import fr.piroxxi.s2le.server.model.Difficulty;
import fr.piroxxi.s2le.server.model.Test;

public interface StoreServiceAsync {

	void createTest(String session, List<Difficulty> difficulties,
			List<Category> categories, int number, boolean chrono,
			AsyncCallback<Test> callback);

	void getCategories(String session, AsyncCallback<List<Category>> callback);

}
