package fr.piroxxi.s2le.client.asside;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.asside.advise.AdvisorActivity;
import fr.piroxxi.s2le.client.events.LoggedInEvent;
import fr.piroxxi.s2le.client.events.LoggedOutEvent;
import fr.piroxxi.s2le.client.events.LoggingEventHandler;
import fr.piroxxi.s2le.client.events.QuestionAnsweredEvent;
import fr.piroxxi.s2le.client.events.QuestionAnsweredEventHandler;
import fr.piroxxi.s2le.client.places.CreateAccountPlace;
import fr.piroxxi.s2le.model.User;

public class AssideActivity extends AbstractActivity implements
		AssideView.Delegate, LoggingEventHandler, QuestionAnsweredEventHandler {

	private AssideView view;
	private final ClientFactory factory;
	private AdvisorActivity activity;

	public AssideActivity(ClientFactory factory) {
		this.factory = factory;
		this.view = factory.getAssideView();
		view.setDelegate(this);

		activity = new AdvisorActivity(factory);

		activity = new AdvisorActivity(factory);

		this.factory.getEventBus().addHandler(LoggedInEvent.TYPE, this);
		this.factory.getEventBus().addHandler(LoggedOutEvent.TYPE, this);
		this.factory.getEventBus().addHandler(QuestionAnsweredEvent.TYPE, this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		activity.start(view.getAdvisorPanel(), eventBus);
	}

	@Override
	public void viewUser(User user) {
		Window.alert("(AssideActivity)TODO: Visit the user " + user);
	}

	@Override
	public void createAccount() {
		this.factory.getPlaceController().goTo(new CreateAccountPlace());
	}

	@Override
	public void loggedIn(LoggedInEvent loggedInEvent) {
		refreshUser();
	}

	private void refreshUser() {
		factory.getStoreService().getUser(
				factory.getSessionManager().getSessionId(),
				factory.getSessionManager().getUserName(),
				new AsyncCallback<User>() {

					@Override
					public void onSuccess(User user) {
						view.showUserStats(user);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("(AssideActivity) - Could not retrieve user "
								+ caught);
					}
				});
	}

	@Override
	public void loggedOut(LoggedOutEvent loggedOutEvent) {
		view.hideUserStats();
	}

	@Override
	public void questionAnswered(QuestionAnsweredEvent questionAnsweredEvent) {
		refreshUser();
	}
}
