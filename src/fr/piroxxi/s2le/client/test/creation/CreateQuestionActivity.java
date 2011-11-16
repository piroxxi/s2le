package fr.piroxxi.s2le.client.test.creation;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.places.EditNewQuestionPlace;
import fr.piroxxi.s2le.model.question.QuestionType;

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
	public void createQuestion(QuestionType questionType) {
		this.clientFactory.getStoreService().createQuestion(
				this.clientFactory.getSessionManager().getSessionId(),
				questionType, new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						if (result != null) {
							clientFactory.getPlaceController().goTo(
									new EditNewQuestionPlace(result));
						} else {
							Window.alert("(CreateQuestionActivity) erreure lors de la création de la question coté serveur");
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("(CreateQuestionActivity) erreure lors de la création de la question coté serveur "
								+ caught);
					}
				});
	}
}
