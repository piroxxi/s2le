package testingApp;

import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.piroxxi.s2le.model.Difficulty;
import fr.piroxxi.s2le.model.question.Question;
import fr.piroxxi.s2le.storage.api.Filter;
import fr.piroxxi.s2le.storage.api.Query;
import fr.piroxxi.s2le.storage.api.Storage;
import fr.piroxxi.s2le.storage.fake.StorageRemplisseur;

/**
 * Shall be removed ! :) (and replaced by a true test :p )
 */
public class SimpleTestingApp {
	private static Storage storage;

	public static void main(String[] args) {
		System.out.println("--- lancement de l'application ---");

		Injector injector = Guice.createInjector(new SimpleTestingModule());
		storage = injector.getInstance(Storage.class);

		StorageRemplisseur.remplissageStorage(storage);
		testQueries();

		injector.getInstance(Application.class);
	}

	private static void testQueries() {
		/*
		 * *** Requêtes sur les questions ***
		 */
		Query<Question> getMediumQuestions = storage
				.createQuery(Question.class);
		getMediumQuestions.addFilter(new Filter<Question>() {
			@Override
			public boolean filter(Question entity) {
				return entity.getDifficulty() == Difficulty.medium;
			}
		});
		List<Question> results = getMediumQuestions.retrieveAsList();
		System.out
				.println("We tried to get all Question whose difficulty is Medium.");
		System.out.println(results);
	}

	
}
