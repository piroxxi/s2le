package fr.piroxxi.s2le.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.piroxxi.s2le.server.injector.ServerModule;
import fr.piroxxi.s2le.server.model.Category;
import fr.piroxxi.s2le.server.model.Difficulty;
import fr.piroxxi.s2le.server.model.Test;
import fr.piroxxi.s2le.server.model.question.Question;
import fr.piroxxi.s2le.server.security.SessionManager;
import fr.piroxxi.s2le.shared.StoreService;
import fr.piroxxi.s2le.shared.security.LogginException;
import fr.piroxxi.s2le.storage.api.Filter;
import fr.piroxxi.s2le.storage.api.Query;
import fr.piroxxi.s2le.storage.api.Storage;

@SuppressWarnings("serial")
public class StoreServiceImpl extends RemoteServiceServlet implements
		StoreService {

	private Storage storage;
	private SessionManager sessionManager;

	public StoreServiceImpl() {
		Injector injector = Guice.createInjector(new ServerModule());
		this.storage = injector.getInstance(Storage.class);
		this.sessionManager = injector.getInstance(SessionManager.class);

	}

	@Override
	public List<Category> getCategories(String session)
			throws IllegalArgumentException, LogginException {
		if (!sessionManager.isValide(session)) {
			throw new LogginException();
		}

		return null;
	}

	@Override
	public Test createTest(String session, final List<Difficulty> difficulties,
			final List<Category> categories, int number, boolean chrono)
			throws IllegalArgumentException, LogginException {
		if (!sessionManager.isValide(session)) {
			throw new LogginException();
		}

		System.out.println("creation du nouvel objet test");

		Query<Question> query = storage.createQuery(Question.class);
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
					System.out.println("la quesstion " + entitie
							+ " a une categorie " + entitie.getCategory());
					// TODO(raphi) : si on a mis la catégorie parente, les
					// enfants doivent aussi être pris (ex: mon choisi fruits et
					// legumes, il faut récupérée les question qui traitent de
					// fuits, et celle qui traitents de légumes);
					return categories.contains(entitie.getCategory());
				}
			});
		}

		List<Question> questions = query.retrieveAsList();
		while (questions.size() > number) {
			questions.remove((int) (Math.random() * questions.size()));
		}

		Test test = new Test(questions, chrono);
		return test;
	}

}
