package fr.piroxxi.s2le.client.test.liste;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.client.ui.cell.QuestionCell;
import fr.piroxxi.s2le.model.question.Question;

public class ListeQuestionsViewImpl extends Composite implements
		ListeQuestionsView {
	interface MyUiBinder extends UiBinder<Widget, ListeQuestionsViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	private Delegate delegate;

	@UiField(provided = true)
	CellList<Question> questions;

	public ListeQuestionsViewImpl() {

		questions = new CellList<Question>(new QuestionCell());

		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@UiHandler("createQuestion")
	public void createQuestion(ClickEvent clickEvent) {
		this.delegate.createQuestion();
	}

	@Override
	public void setQuestions(Question[] result) {
		List<Question> questions = new ArrayList<Question>();
		for (Question q : result) {
			questions.add(q);
		}
		this.questions.setRowData(questions);
	}
}
