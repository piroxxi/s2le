package fr.piroxxi.s2le.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class LigneFormulaire extends Composite {
	interface MyUiBinder extends UiBinder<Widget, LigneFormulaire> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	SpanElement helpText;

	@UiField
	Label title;

	@UiField
	HTMLPanel help;

	@UiField
	SimplePanel component;

	@UiField
	Label error;

	public LigneFormulaire() {
		initWidget(uiBinder.createAndBindUi(this));

		this.help.setVisible(false);
		this.error.setVisible(false);
	}

	/**
	 * Définit le widget affiché.
	 */
	@UiChild(limit = 1, tagname = "content")
	public void setContent(Widget widget) {
		this.component.setWidget(widget);
	}

	public void setHelp(String helpText) {
		this.help.setVisible(true);
		this.helpText.setInnerText(helpText);
	}

	public void setLibelle(String libelle) {
		this.title.setText(libelle);
	}

	public void setError(String error) {
		if (error == null || error.isEmpty()) {
			this.error.setVisible(false);
		} else {
			this.error.setVisible(true);
			this.error.setText(error);
		}

	}
}
