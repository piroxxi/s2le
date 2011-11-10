package testingApp.activity.test;

import java.util.List;

import javax.swing.JPanel;

import testingApp.ui.Activity;
import testingApp.ui.placeControler.PlaceControler;

import com.google.inject.Inject;

import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;
import fr.piroxxi.s2le.model.Test;
import fr.piroxxi.s2le.model.question.Question;
import fr.piroxxi.s2le.storage.api.Filter;
import fr.piroxxi.s2le.storage.api.Query;
import fr.piroxxi.s2le.storage.api.Storage;

public class TestConfigurationActivity extends Activity implements
		TestConfigurationView.Delegate {

	private final TestConfigurationView view;
	private final Storage storage;
	private final PlaceControler placeControler;

	@Inject
	public TestConfigurationActivity(PlaceControler placeControler,
			Storage storage, TestConfigurationView view) {
		this.placeControler = placeControler;
		this.storage = storage;
		this.view = view;
	}

	@Override
	public void startActivity(JPanel panel) {
		panel.removeAll();
		view.setDelegate(this);
		panel.add(view);
	}

	@Override
	public void startTest(final List<Difficulty> difficulties,
			final List<Category> categories, int number, boolean chrono) {
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
		placeControler.goTo(new TestPlace(test));
	}
}
