package fr.piroxxi.s2le.client.test.liste;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import fr.piroxxi.s2le.model.question.Question;

public class ListeQuestionsViewImpl extends Composite implements
		ListeQuestionsView {
	interface MyUiBinder extends UiBinder<Widget, ListeQuestionsViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	private Delegate delegate;

	@UiField(provided = true)
	CellTable<Question> questions;

	@UiField
	HTMLPanel mainPanel;

	@UiField
	SimplePanel errorPanel;

	public ListeQuestionsViewImpl() {

		questions = new CellTable<Question>();

		Column<Question, String> type = new Column<Question, String>(
				new TextCell()) {
			@Override
			public String getValue(Question object) {
				return object.getType();
			}
		};
		type.setSortable(true);
		questions.addColumn(type, "type");

		Column<Question, String> category = new Column<Question, String>(
				new TextCell()) {
			@Override
			public String getValue(Question object) {
				return (object.getCategory() != null) ? object.getCategory()
						.getCategoryName() : " - no category -";
			}
		};
		category.setSortable(true);
		questions.addColumn(category, "category");

		Column<Question, String> resume = new Column<Question, String>(
				new TextCell()) {
			@Override
			public String getValue(Question object) {
				return object.getResume();
			}
		};
		resume.setSortable(true);
		questions.addColumn(resume, "resumé");

		final SingleSelectionModel<Question> selectionModel = new SingleSelectionModel<Question>();
		questions.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						delegate.editQuestion(selectionModel
								.getSelectedObject());
					}
				});

		initWidget(uiBinder.createAndBindUi(this));

		errorPanel.setVisible(false);
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

	@Override
	public void showErrorPanel(String string) {
		mainPanel.setVisible(false);
		errorPanel.setVisible(true);
		errorPanel.setWidget(new Label(string));
	}

	@Override
	public void hideErrorPanel() {
		mainPanel.setVisible(true);
		errorPanel.setVisible(false);
	}
}
