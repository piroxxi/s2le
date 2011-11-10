package fr.piroxxi.s2le.client.test.questions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.model.question.MultiChoicesQuestion;

public class MultiChoicesQuestionPanel extends Composite implements
		QuestionPanel<MultiChoicesQuestion>, ClickHandler {
	interface MyUiBinder extends UiBinder<Widget, MultiChoicesQuestionPanel> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	Label question;

	@UiField
	HorizontalPanel answers;

	@UiField
	Label error;

	@UiField
	Button next;

	private Delegate delegate;

	private boolean answer;

	private MultiChoicesQuestion questionAsken;

	public MultiChoicesQuestionPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		next.setVisible(false);
		error.setVisible(false);
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showQuestion(MultiChoicesQuestion question) {
		questionAsken = question;
		this.answer = false;
		this.question.setText(question.getQuestion());
		for (String answer : question.getAnswers()) {
			Button button = new Button(answer);
			button.addClickHandler(this);
			this.answers.add(button);
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		Button origin = (Button) event.getSource();
		if (origin.getText().equals(
				questionAsken.getAnswers()[questionAsken.getRightAnswer()])) {
			error.setText("bonne reponse!");
			this.answer = true;
		} else {
			error.setText("Faux! La bonne reponse était \""
					+ questionAsken.getAnswers()[questionAsken.getRightAnswer()]
					+ "\" .");
			this.answer = false;
		}

		next.setVisible(true);
		error.setVisible(true);
		answers.setVisible(false);
	}

	@UiHandler("next")
	public void start(ClickEvent event) {
		if (delegate != null) {
			delegate.hasAnswered(answer);
		}
	}
}
