package fr.piroxxi.s2le.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StoreServiceAsync {

	void createTest(String session, int number, boolean chrono,
			AsyncCallback<String> callback);

}
