package testingApp;

import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;
import fr.piroxxi.s2le.model.question.MultyChoicesQuestions;
import fr.piroxxi.s2le.model.question.Question;
import fr.piroxxi.s2le.model.question.Translation;
import fr.piroxxi.s2le.model.question.YesNoQuestion;
import fr.piroxxi.s2le.storage.api.Filter;
import fr.piroxxi.s2le.storage.api.Query;
import fr.piroxxi.s2le.storage.api.Storage;

/**
 * Shall be removed ! :) (and replaced by a true test :p )
 */
public class SimpleTestingApp {
	private static Storage storage;

	public static void main(String[] args) {
		System.out.println("--- lancement de l'application ---");

		Injector injector = Guice.createInjector(new SimpleTestingModule());
		storage = injector.getInstance(Storage.class);

		remplissageStorage();
		testQueries();

		Application application = injector.getInstance(Application.class);
		application.start();
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

	private static void remplissageStorage() {
		/*
		 * *** Creation des categories ***
		 */
		Category cultureGenerale = new Category(null, "culture general");
		storage.store(Category.class, cultureGenerale);

		Category grammaire = new Category(null, "grammaire");
		storage.store(Category.class, grammaire);

		Category couleurs = new Category(null, "culture general");
		storage.store(Category.class, couleurs);

		Category mondeEntreprise = new Category(null, "monde de l'Entreprise");
		storage.store(Category.class, mondeEntreprise);

		Category fruitsEtLegumes = new Category(null, "fruits et legumes");
		storage.store(Category.class, fruitsEtLegumes);

		Category fruits = new Category(fruitsEtLegumes.getId(), "fruits");
		storage.store(Category.class, fruits);

		Category legumes = new Category(fruitsEtLegumes.getId(), "legumes");
		storage.store(Category.class, legumes);

		/*
		 * *** Creation des questions ***
		 */
		Translation boardOfDirectors = new Translation("piroxxi",
				mondeEntreprise, Difficulty.hard, "(a) Board of Directors",
				"(Un) conseil (d')administration");
		storage.store(Question.class, boardOfDirectors);

		Translation technicalAdvisor = new Translation("piroxxi",
				mondeEntreprise, Difficulty.medium, "(a) Technical advisor",
				"(Un) conseiller technique");
		storage.store(Question.class, technicalAdvisor);

		YesNoQuestion orangeIsAColor = new YesNoQuestion("piroxxi",
				cultureGenerale, Difficulty.verySimple,
				"The word 'orange' can refer either to a fruit and a color ?",
				true);
		storage.store(Question.class, orangeIsAColor);

		MultyChoicesQuestions whichWitchOrWhat = new MultyChoicesQuestions(
				"piroxxi", grammaire, Difficulty.medium,
				"Complete this sentence : '..... one do you prefer?'",
				new String[] { "witch", "which", "what" }, 1);
		storage.store(Question.class, whichWitchOrWhat);

		MultyChoicesQuestions manyMuch = new MultyChoicesQuestions(
				"piroxxi",
				grammaire,
				Difficulty.medium,
				"Complete this sentence : 'I lost so ... fish because my aquarium's PH was too low.'",
				new String[] { "much", "many" }, 1);
		storage.store(Question.class, manyMuch);
	}
}
