package fr.piroxxi.s2le.client.test.questions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
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
	TextBox answerBox;

	@UiField
	Button ok;

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

		answerBox.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showQuestion(Translation question) {
		questionAsken = question;
		this.answer = false;
		this.question.setText(Translation.toReadableFormat(question
				.getEnglishWord()));

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			public void execute() {
				answerBox.setFocus(true);
			}
		});

	}

	@UiHandler("answerBox")
	public void onEnter(KeyPressEvent event) {
		if (KeyCodes.KEY_ENTER == event.getNativeEvent().getKeyCode()) {
			if (next.isVisible()) {
				next(null);
			} else {
				ok();
			}
		}
	}

	@UiHandler("ok")
	public void ok(ClickEvent event) {
		ok();
	}

	private void ok() {
		this.answer = answerBox
				.getText()
				.toLowerCase()
				.matches(
						questionAsken.getFrenchWord().toLowerCase()
								.replace(")", "|)").replace(" ", " ?"));

		if (answer) {
			error.setText("bonne reponse!");
		} else {
			error.setText("Faux! La bonne reponse était \""
					+ Translation.toReadableFormat(questionAsken
							.getFrenchWord()) + "\" .");
		}

		next.setVisible(true);
		error.setVisible(true);
		error.setStyleName((answer) ? "question_right" : "question_false");
		answerBox.setEnabled(false);
		ok.setVisible(false);
	}

	@UiHandler("next")
	public void next(ClickEvent event) {
		if (delegate != null) {
			delegate.hasAnswered(answer);
		}
	}
}
