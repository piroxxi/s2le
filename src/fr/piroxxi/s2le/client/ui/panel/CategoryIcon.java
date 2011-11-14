package fr.piroxxi.s2le.client.ui.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.model.Category;

public class CategoryIcon extends Composite {
	interface Delegate {
		public void removeFromSelection(CategoryIcon categoryIcon);
	}

	interface MyUiBinder extends UiBinder<Widget, CategoryIcon> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	private final Category category;
	private final Delegate delegate;

	@UiField
	Label name;

	public CategoryIcon(Category category, Delegate delegate) {
		this.category = category;
		this.delegate = delegate;
		initWidget(uiBinder.createAndBindUi(this));
		name.setText(category.getCategoryName());
	}

	@UiHandler("x")
	public void remove(ClickEvent event) {
		delegate.removeFromSelection(this);
	}

	public Category getCategory() {
		return category;
	}
}
