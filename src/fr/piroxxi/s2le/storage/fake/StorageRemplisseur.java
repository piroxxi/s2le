package fr.piroxxi.s2le.storage.fake;

import fr.piroxxi.s2le.model.Advise;
import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;
import fr.piroxxi.s2le.model.question.MultiChoicesQuestion;
import fr.piroxxi.s2le.model.question.Question;
import fr.piroxxi.s2le.model.question.SimpleQuestion;
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

		Category cuisine = new Category(null, "cuisine");
		storage.store(Category.class, cuisine);

		Category mondeEntreprise = new Category(null, "monde de l'Entreprise");
		storage.store(Category.class, mondeEntreprise);

		Category fruitsEtLegumes = new Category(null, "fruits et legumes");
		storage.store(Category.class, fruitsEtLegumes);

		Category fruits = new Category(fruitsEtLegumes.getId(), "fruits");
		storage.store(Category.class, fruits);
		storage.store(Category.class, new Category(fruits.getId(),
				"fruits exotiques"));

		Category legumes = new Category(fruitsEtLegumes.getId(), "legumes");
		storage.store(Category.class, legumes);
		storage.store(Category.class, new Category(legumes.getId(),
				"legumes d'été"));
		storage.store(Category.class, new Category(legumes.getId(),
				"legumes d'hivers"));

		/*
		 * *** Creation des conseils ***
		 */
		storage.store(
				Advise.class,
				new Advise(
						"On prononce \"january the 15th\", tandis que l'on écrit \"january 15th\".",
						"En anglais, toutes les dates s'écrivent de manière ordinale (1st, 2nd, 3rd, 4th, etc.), et à l'orale, on prononce le \"the\" tandis qu'il ne s'écrit pas!"));
		storage.store(
				Advise.class,
				new Advise(
						"Lorsque l'on ne sait pas quel terme employé pour désigner une personne de sexe féminin, on dit Ms.",
						"L'anglais dispose de trois noms pour présenter une femme, Miss (si la personne n'est pas mariée), Mrs (si la personne est mariée) et Ms (si l'on ne sait pas ou que l'on ne souhaite pas le mentionner)."));
		storage.store(
				Advise.class,
				new Advise(
						"On se rends en un lieu \"by car\", \"by plane\", ou \"by boat\", mais si on y va à pieds, alors c'est \"on foot\".",
						"TODO"));
		storage.store(
				Advise.class,
				new Advise(
						"Les anglais utilisent le verbe \"to practise\" tandis que les americains utilisent \"to practice\".",
						"Le nom quand à lui reste \"practice\" quel que soit le pays."));

		/*
		 * *** Creation des questions ***
		 */
		Translation boardOfDirectors = new Translation("piroxxi",
				mondeEntreprise, Difficulty.hard, "(a) Board of Directors",
				"(Un|le) conseil (d')administration");
		storage.store(Question.class, boardOfDirectors);

		Translation technicalAdvisor = new Translation("piroxxi",
				mondeEntreprise, Difficulty.medium,
				"(a|The) Technical advisor", "(Un|Le) conseiller technique");
		storage.store(Question.class, technicalAdvisor);

		Translation toPlummet = new Translation("piroxxi", mondeEntreprise,
				Difficulty.medium, "(to) plummet",
				"(dégringoler|s'effondrer|effondrer)");
		storage.store(Question.class, toPlummet);

		Translation thickSteak = new Translation("piroxxi", cuisine,
				Difficulty.medium, "(a) sick steak", "(Un) steak (épais|epais)");
		storage.store(Question.class, thickSteak);

		YesNoQuestion orangeIsAColor = new YesNoQuestion("piroxxi",
				cultureGenerale, Difficulty.verySimple,
				"The word 'orange' can refer either to a fruit and a color ?",
				true);
		storage.store(Question.class, orangeIsAColor);

		SimpleQuestion catered = new SimpleQuestion(
				"piroxxi",
				mondeEntreprise,
				Difficulty.medium,
				"Quel est le sens du mot 'catered' dans la phrase suivante : \"A professionally catered event.\"",
				"(traiteur|pris en charge)");
		/**
		 * * D'oh! on a grave un problème! Dans le cas ou plusieurs mots sont
		 * accéptables...
		 * <p>
		 * Surement qu'on devrait faire un truc sexy du style: un liste de
		 * "rexep" :p :)
		 * <p>
		 * Ouai... faisont ca :)
		 */
		storage.store(Question.class, catered);

		MultiChoicesQuestion whichWitchOrWhat = new MultiChoicesQuestion(
				"piroxxi", grammaire, Difficulty.medium,
				"Complete this sentence : \"... one do you prefer?\"",
				new String[] { "witch", "which", "what" }, 1);
		storage.store(Question.class, whichWitchOrWhat);

		MultiChoicesQuestion multi1 = new MultiChoicesQuestion(
				"piroxxi",
				grammaire,
				Difficulty.medium,
				"Complete this sentence : \"I'll ring you as soon as I ... there.\"",
				new String[] { "get", "will get", "shall get", "will have got" },
				0);
		storage.store(Question.class, multi1);

		MultiChoicesQuestion manyMuch = new MultiChoicesQuestion(
				"piroxxi",
				grammaire,
				Difficulty.medium,
				"Complete this sentence : \"I lost so ... fish because my aquarium's PH was too low.\"",
				new String[] { "much", "many" }, 1);
		storage.store(Question.class, manyMuch);
	}
}
