package fr.piroxxi.s2le.client.test.questions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.model.question.Translation;

public class TranslationQuestionPanel extends Composite implements
		QuestionPanel<Translation> {
	interface MyUiBinder extends UiBinder<Widget, TranslationQuestionPanel> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	Label question;

	@UiField
	TextBox answers;

	@UiField
	Label error;

	@UiField
	Button next;

	private Delegate delegate;

	private boolean answer;

	private Translation questionAsken;

	public TranslationQuestionPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		next.setVisible(false);
		error.setVisible(false);
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showQuestion(Translation question) {
		questionAsken = question;
		this.answer = false;
		this.question.setText(question.getEnglishWord());
	}

	@UiHandler("ok")
	public void ok(ClickEvent event) {
		if (answers.getText().equalsIgnoreCase(questionAsken.getFrenchWord())) {
			error.setText("bonne reponse!");
			this.answer = true;
		} else {
			error.setText("Faux! La bonne reponse était \""
					+ questionAsken.getFrenchWord() + "\" .");
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
