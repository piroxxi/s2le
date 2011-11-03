package testingApp.ui.widget;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import testingApp.RefreshingEvent;
import testingApp.ui.eventBus.EventBus;
import fr.piroxxi.s2le.shared.model.Category;
import fr.piroxxi.s2le.storage.api.Filter;
import fr.piroxxi.s2le.storage.api.Query;
import fr.piroxxi.s2le.storage.api.Storage;

public class CategorySelector extends JPanel implements ActionListener {
	private static final long serialVersionUID = 603433960979739696L;

	public JComboBox comboBox;
	private JPanel proposition;
	private Map<String, Category> categories;
	private List<String> bonBahJeSaisCestMocheMaisSinonCaMarchePasCommeJeVeuxAlorsJeFaisALaVasVite = new ArrayList<String>();
	private List<Category> selectedCategories = new ArrayList<Category>();

	private final EventBus eventBus;
	private final Storage storage;

	public CategorySelector(Storage storage, EventBus eventBus) {

		this.storage = storage;
		this.eventBus = eventBus;
		this.setLayout(new GridLayout());

		Query<Category> parents = storage.createQuery(Category.class);
		parents.addFilter(new Filter<Category>() {

			@Override
			public boolean filter(Category entitie) {
				return entitie.getParentId() == null;
			}
		});
		categories = new HashMap<String, Category>();
		for (Category cat : parents.retrieveAsList()) {
			addSons(categories, cat, "   ");
		}
		comboBox = new JComboBox(
				bonBahJeSaisCestMocheMaisSinonCaMarchePasCommeJeVeuxAlorsJeFaisALaVasVite
						.toArray());
		this.add(comboBox, BorderLayout.WEST);
		comboBox.addActionListener(this);

		proposition = new JPanel();

		proposition.setLayout(new BoxLayout(proposition, BoxLayout.PAGE_AXIS));

		this.add(proposition, BorderLayout.CENTER);

	}

	private void addSons(Map<String, Category> categories, final Category cat,
			String dec) {
		bonBahJeSaisCestMocheMaisSinonCaMarchePasCommeJeVeuxAlorsJeFaisALaVasVite
				.add(dec + cat.getCategoryName());
		categories.put(dec + cat.getCategoryName(), cat);
		Query<Category> sons = storage.createQuery(Category.class);
		sons.addFilter(new Filter<Category>() {
			@Override
			public boolean filter(Category entitie) {
				return cat.getId().equals(entitie.getParentId());
			}
		});
		for (Category son : sons.retrieveAsList()) {
			addSons(categories, son, dec + "   ");
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = (String) this.comboBox.getSelectedItem();
		Category category = categories.get(s);
		if (!selectedCategories.contains(category)) {

			Selectgleton label = new Selectgleton(category);
			proposition.add(label);
			selectedCategories.add(category);
			eventBus.publishEvent(RefreshingEvent.class, new RefreshingEvent());

		}
	}

	private class Selectgleton extends JPanel implements ActionListener {
		private static final long serialVersionUID = 2026331806219464452L;

		private final Category c;

		public Selectgleton(Category c) {
			this.c = c;
			JButton button = new JButton("x");
			button.addActionListener(this);
			this.add(new Label(c.getCategoryName()));
			this.add(button);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			proposition.remove(this);
			eventBus.publishEvent(RefreshingEvent.class, new RefreshingEvent());
			selectedCategories.remove(c);
		}
	}

	public List<Category> getSelectedCategories() {
		return selectedCategories;
	}
}
