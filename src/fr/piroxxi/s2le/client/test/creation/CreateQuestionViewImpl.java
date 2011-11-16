package fr.piroxxi.s2le.client.test.creation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.model.question.QuestionType;

public class CreateQuestionViewImpl extends Composite implements
		CreateQuestionView {
	interface MyUiBinder extends UiBinder<Widget, CreateQuestionViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	private Delegate delegate;

	public CreateQuestionViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@UiHandler("multiChoiceQuestion")
	public void multiChoiceQuestion(ClickEvent clickEvent) {
		delegate.createQuestion(QuestionType.MultiChoiceQuestion);
	}

	@UiHandler("yesNoQuestion")
	public void yesNoQuestion(ClickEvent clickEvent) {
		delegate.createQuestion(QuestionType.YesNoQuestion);
	}

	@UiHandler("translationQuestion")
	public void translationQuestion(ClickEvent clickEvent) {
		delegate.createQuestion(QuestionType.Translation);
	}

	@UiHandler("simpleQuestion")
	public void simpleQuestion(ClickEvent clickEvent) {
		delegate.createQuestion(QuestionType.SimpleQuestion);
	}
}
