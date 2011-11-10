package fr.piroxxi.s2le.client.ui.panel;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.model.Category;

public class CategoryChooser extends Composite {
	interface MyUiBinder extends UiBinder<Widget, CategoryChooser> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	private Category[] categories;

	@UiField
	ListBox categoriesLB;

	@UiField
	HorizontalPanel categoriesHP;

	public CategoryChooser() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setCategories(Category[] categories) {
		this.categories = categories;
		while (categoriesLB.getItemCount() > 0)
			categoriesLB.removeItem(0);
		addSonsof(null, "");
	}

	private void addSonsof(String id, String prefix) {
		for (Category cat : categories) {
			if ((id == null && cat.getParentId() == null)
					|| (id != null && id.equals(cat.getParentId()))) {
				categoriesLB.addItem(prefix + cat.getCategoryName(),
						cat.getId());
				addSonsof(cat.getId(), prefix + "- ");
			}
		}
	}

	@UiHandler("categoriesLB")
	public void selectCategory(ChangeEvent event) {
		String categoryId = categoriesLB.getValue(categoriesLB
				.getSelectedIndex());
		for (Category cat : categories) {
			if (cat.getId().equals(categoryId)) {
				addToSelection(cat);
			}
		}
	}

	List<Category> selectedCategories = new ArrayList<Category>();

	private void addToSelection(Category cat) {
		selectedCategories.add(cat);
		categoriesHP.add(new CategoryS(cat));
	}

	private void removeFromSelection(CategoryS cat) {
		selectedCategories.remove(cat.getCategory());
		categoriesHP.remove(cat);
	}

	public List<Category> getSelectedCategories() {
		return selectedCategories;
	}

	class CategoryS extends HorizontalPanel implements ClickHandler {
		private final Category cat;

		public CategoryS(Category cat) {
			this.cat = cat;
			Button b = new Button("x");
			b.addClickHandler(this);
			this.add(b);
			this.add(new Label(cat.getCategoryName()));
		}

		@Override
		public void onClick(ClickEvent event) {
			removeFromSelection(this);
		}

		public Category getCategory() {
			return cat;
		}
	}
}
