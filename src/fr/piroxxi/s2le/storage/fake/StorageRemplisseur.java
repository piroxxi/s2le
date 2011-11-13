package fr.piroxxi.s2le.storage.fake;

import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;
import fr.piroxxi.s2le.model.question.MultiChoicesQuestion;
import fr.piroxxi.s2le.model.question.Question;
import fr.piroxxi.s2le.model.question.Translation;
import fr.piroxxi.s2le.model.question.YesNoQuestion;
import fr.piroxxi.s2le.storage.api.Storage;

public class StorageRemplisseur {
	public static void remplissageStorage(Storage storage) {
		/*
		 * *** Creation des categories ***
		 */
		Category cultureGenerale = new Category(null, "culture general");
		storage.store(Category.class, cultureGenerale);

		Category grammaire = new Category(null, "grammaire");
		storage.store(Category.class, grammaire);

		Category couleurs = new Category(null, "couleures");
		storage.store(Category.class, couleurs);

		Category mondeEntreprise = new Category(null, "monde de l'Entreprise");
		storage.store(Category.class, mondeEntreprise);

		Category fruitsEtLegumes = new Category(null, "fruits et legumes");
		storage.store(Category.class, fruitsEtLegumes);

		Category fruits = new Category(fruitsEtLegumes.getId(), "fruits");
		storage.store(Category.class, fruits);
		storage.store(Category.class, new Category(fruits.getId(), "fruits exotiques"));

		Category legumes = new Category(fruitsEtLegumes.getId(), "legumes");
		storage.store(Category.class, legumes);
		storage.store(Category.class, new Category(legumes.getId(), "legumes d'été"));
		storage.store(Category.class, new Category(legumes.getId(), "legumes d'hivers"));

		/*
		 * *** Creation des questions ***
		 */
		Translation boardOfDirectors = new Translation("piroxxi",
				mondeEntreprise, Difficulty.hard, "(a) Board of Directors",
				"(Un|le) conseil (d')administration");
		storage.store(Question.class, boardOfDirectors);

		Translation technicalAdvisor = new Translation("piroxxi",
				mondeEntreprise, Difficulty.medium, "(a|The) Technical advisor",
				"(Un|Le) conseiller technique");
		storage.store(Question.class, technicalAdvisor);

		YesNoQuestion orangeIsAColor = new YesNoQuestion("piroxxi",
				cultureGenerale, Difficulty.verySimple,
				"The word 'orange' can refer either to a fruit and a color ?",
				true);
		storage.store(Question.class, orangeIsAColor);

		MultiChoicesQuestion whichWitchOrWhat = new MultiChoicesQuestion(
				"piroxxi", grammaire, Difficulty.medium,
				"Complete this sentence : '..... one do you prefer?'",
				new String[] { "witch", "which", "what" }, 1);
		storage.store(Question.class, whichWitchOrWhat);

		MultiChoicesQuestion manyMuch = new MultiChoicesQuestion(
				"piroxxi",
				grammaire,
				Difficulty.medium,
				"Complete this sentence : 'I lost so ... fish because my aquarium's PH was too low.'",
				new String[] { "much", "many" }, 1);
		storage.store(Question.class, manyMuch);
	}
}
