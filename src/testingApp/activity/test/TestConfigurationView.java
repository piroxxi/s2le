package testingApp.activity.test;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import testingApp.ui.eventBus.EventBus;
import testingApp.ui.widget.CategorySelector;

import com.google.inject.Inject;

import fr.piroxxi.s2le.shared.model.Category;
import fr.piroxxi.s2le.shared.model.Difficulty;
import fr.piroxxi.s2le.storage.api.Storage;

public class TestConfigurationView extends JPanel implements ActionListener {
	public interface Delegate {
		void startTest(List<Difficulty> difficulties,
				List<Category> categories, int number, boolean chrono);
	}

	private static final long serialVersionUID = 5681190850492998761L;
	private Delegate delegate;

	private CategorySelector category;
	private Checkbox chrono;
	private TextField number;
	private Checkbox[] difs;

	@Inject
	public TestConfigurationView(Storage storage, EventBus eventBus) {

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.add(new Label("-- Choix du type de test --"));

		category = new CategorySelector(storage, eventBus);
		this.add(thisMethodeIsMagic(new Label("catégorie : "), category));

		JPanel dif = new JPanel();
		difs = new Checkbox[Difficulty.values().length];
		for (int i = 0; i < difs.length; i++) {
			difs[i] = new Checkbox(Difficulty.values()[i].getLibelle());
			dif.add(difs[i]);
		}
		this.add(thisMethodeIsMagic(new Label("difficulté : "), dif));

		number = new TextField("10");
		this.add(thisMethodeIsMagic(new Label("nombre de questions : "), number));

		chrono = new Checkbox("oui");
		this.add(thisMethodeIsMagic(new Label("mode chronométré : "), chrono));

		JButton ok = new JButton("start test!");
		ok.addActionListener(this);
		this.add(ok);
	}

	/**
	 * Ugliest code ever! Put 2 components on a line, using BorderLayout.
	 */
	private JPanel thisMethodeIsMagic(Component component1, Component component2) {
		JPanel ouaicheMaGeule = new JPanel();
		ouaicheMaGeule.setLayout(new BorderLayout());
		ouaicheMaGeule.add(component1, BorderLayout.WEST);
		ouaicheMaGeule.add(component2, BorderLayout.CENTER);
		return ouaicheMaGeule;
	}

	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<Difficulty> difficulties = new ArrayList<Difficulty>();
		for (int i = 0; i < difs.length; i++) {
			if (difs[i].getState()) {
				difficulties.add(Difficulty.values()[i]);
			}
		}
		try {
			int number = Integer.parseInt(this.number.getText());
			delegate.startTest(difficulties, category.getSelectedCategories(),
					number, chrono.getState());
		} catch (java.lang.NumberFormatException exception) {
			JOptionPane.showMessageDialog(null,
					"Le nombre que vous avez saisi n'a pas le bon format",
					"Erreure", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
