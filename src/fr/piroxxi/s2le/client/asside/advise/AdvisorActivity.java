package fr.piroxxi.s2le.client.asside.advise;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.model.Advise;

public class AdvisorActivity extends AbstractActivity implements
		AdvisorView.Delegate {

	private final ClientFactory factory;
	private AdvisorView view;
	private Advise currentAdvise;
	private Timer timer;

	public AdvisorActivity(ClientFactory factory) {
		this.factory = factory;
		view = factory.getAdvisorView();
		view.setDelegate(this);
		timer = new Timer() {

			@Override
			public void run() {
				updateAdvise();
			}
		};

		updateAdvise();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		timer.scheduleRepeating(10000);
	}

	@Override
	public void onStop() {
		timer.cancel();
	}

	private void updateAdvise() {
		this.factory.getStoreService().getRandomAdvise(
				factory.getSessionManager().getSessionId(),
				new AsyncCallback<Advise>() {

					@Override
					public void onSuccess(Advise result) {
						currentAdvise = result;
						view.showAdvise(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("(AdvisorActivity) unnable to get next advise");
					}
				});
	}

	@Override
	public void showAdvise() {
		Window.alert("TODO AdvisorActivity: goto advise " + currentAdvise);
	}
}
