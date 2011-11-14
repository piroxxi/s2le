package fr.piroxxi.s2le.client.test.creation;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.model.question.Question;

public class CreateQuestionActivity extends AbstractActivity implements
		CreateQuestionView.Delegate {

	private final ClientFactory clientFactory;
	private final CreateQuestionView view;

	public CreateQuestionActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.view = this.clientFactory.getCreateQuestionView();
		this.view.setDelegate(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

	@Override
	public void createQuestion(Question question) {
		Window.alert("toto");
	}

}
