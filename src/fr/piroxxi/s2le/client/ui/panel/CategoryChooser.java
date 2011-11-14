package fr.piroxxi.s2le.client.ui.panel;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.client.ui.panel.CategoryIcon.Delegate;
import fr.piroxxi.s2le.model.Category;

public class CategoryChooser extends Composite implements Delegate {
	interface MyUiBinder extends UiBinder<Widget, CategoryChooser> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	private Category[] categories;

	@UiField
	ListBox categoriesLB;

	@UiField
	FlowPanel categoriesHP;

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
		if (!selectedCategories.contains(cat)) {
			selectedCategories.add(cat);
			categoriesHP.add(new CategoryIcon(cat, this));
		}
	}

	@Override
	public void removeFromSelection(CategoryIcon cat) {
		selectedCategories.remove(cat.getCategory());
		categoriesHP.remove(cat);
	}

	public List<Category> getSelectedCategories() {
		return selectedCategories;
	}
}
