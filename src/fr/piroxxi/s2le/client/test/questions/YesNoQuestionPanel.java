package fr.piroxxi.s2le.client.test.questions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.model.question.YesNoQuestion;

public class YesNoQuestionPanel extends Composite implements
		QuestionPanel<YesNoQuestion> {
	interface MyUiBinder extends UiBinder<Widget, YesNoQuestionPanel> {
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

	private YesNoQuestion questionAsken;

	public YesNoQuestionPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		next.setVisible(false);
		error.setVisible(false);
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showQuestion(YesNoQuestion question) {
		questionAsken = question;
		this.answer = false;
		this.question.setText(question.getQuestion());

	}

	@UiHandler("no")
	public void no(ClickEvent event) {
		answer = !questionAsken.isAnswer();
		res();
	}

	@UiHandler("yes")
	public void yes(ClickEvent event) {
		answer = questionAsken.isAnswer();
		res();
	}

	private void res() {
		next.setVisible(true);
		error.setVisible(true);
		error.setStyleName((answer)?"question_right":"question_false");
		if (!answer) {
			error.setText("Mauvaise reponse! La bonne réponse était "+((questionAsken.isAnswer())?"oui":"non")+".");
		} else {
			error.setText("Bonne reponse!");
		}
		answers.setVisible(false);
	}

	@UiHandler("next")
	public void start(ClickEvent event) {
		if (delegate != null) {
			delegate.hasAnswered(answer);
		}
	}
}
