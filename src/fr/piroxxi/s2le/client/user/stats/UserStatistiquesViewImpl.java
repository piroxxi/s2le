package fr.piroxxi.s2le.client.user.stats;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.model.User;

public class UserStatistiquesViewImpl extends Composite implements
		UserStatistiquesView {
	interface MyUiBinder extends UiBinder<Widget, UserStatistiquesViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@SuppressWarnings("unused")
	private Delegate delegate;

	@UiField
	SpanElement nom;

	@UiField
	Label nbQuestions;

	@UiField
	Label nbCorrectAnswer;

	@UiField
	Label nbTestsStarted;

	@UiField
	Label nbTestsEnded;

	@UiField
	VerticalPanel formulaire;

	@UiField
	Label error;

	public UserStatistiquesViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void showUserStats(User user) {
		this.nom.setInnerText(user.getName());
		this.nbQuestions.setText(user.getNbQuestionsRepondues() + "");
		this.nbCorrectAnswer.setText(user.getNbQuestionsJustes() + "");
		this.nbTestsStarted.setText(user.getNbTestsCommencees() + "");
		this.nbTestsEnded.setText(user.getNbTestsFinis() + "");
	}

	@Override
	public void hideErrorPanel() {
		this.error.setVisible(false);
		this.formulaire.setVisible(true);
	}

	@Override
	public void showErrorPanel(String error) {
		this.error.setVisible(true);
		this.error.setText(error);
		this.formulaire.setVisible(false);
	}

}
