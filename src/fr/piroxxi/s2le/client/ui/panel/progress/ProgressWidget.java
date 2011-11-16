package fr.piroxxi.s2le.client.ui.panel.progress;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ProgressWidget extends Composite {
	interface MyStyle extends CssResource {
		String toDo();

		String done();

		String allDone();

		String allToDo();
	}

	interface MyUiBinder extends UiBinder<Widget, ProgressWidget> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	MyStyle style;

	@UiField
	DivElement panel;

	@UiField
	Label percentage;

	public ProgressWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPercentage(int percentage) {
		if (percentage <= 1) {
			this.panel.setInnerHTML("<div class=\"" + style.allToDo()
					+ "\"></div>");
		} else if (percentage >= 99) {
			this.panel.setInnerHTML("<div class=\"" + style.allDone()
					+ "\"></div>");
		} else {
			this.panel.setInnerHTML("<div class=\"" + style.done()
					+ "\" style=\"width: " + (percentage - 1) + "%;\" ></div>"
					+ "<div class=\"" + style.toDo() + "\" style=\"width: "
					+ ((100 - percentage) - 1) + "%;\" ></div>");
		}
		this.percentage.setText(percentage + " %");
	}

}
