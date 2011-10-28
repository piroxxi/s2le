package fr.piroxxi.s2le.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.piroxxi.s2le.server.model.Category;
import fr.piroxxi.s2le.server.model.Difficulty;
import fr.piroxxi.s2le.server.model.Test;
import fr.piroxxi.s2le.shared.security.LogginException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("store")
public interface StoreService extends RemoteService {
	Test createTest(String session, List<Difficulty> difficulties, List<Category> categories,
			int number, boolean chrono) throws IllegalArgumentException, LogginException;
	
	List<Category> getCategories(String session) throws IllegalArgumentException,LogginException;
}
