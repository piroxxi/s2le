package fr.piroxxi.s2le.client.test.liste;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;

public class ListeQuestionsActivity extends AbstractActivity implements
		ListeQuestionsView.Delegate {

	private final ClientFactory clientFactory;
	private ListeQuestionsView view;

	public ListeQuestionsActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.view = clientFactory.getListeTestView();
		view.setDelegate(this);
		panel.setWidget(view);
	}

}
