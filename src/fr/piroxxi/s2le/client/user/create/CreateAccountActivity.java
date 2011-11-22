package fr.piroxxi.s2le.client.user.create;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.places.CreateAccountConfirmationPlace;

public class CreateAccountActivity extends AbstractActivity implements
		CreateAccountView.Delegate {

	private ClientFactory clientFactory;
	private CreateAccountView view;

	public CreateAccountActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.view = clientFactory.getCreateAccountView();
		view.setDelegate(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

	@Override
	public void createAccount(final String name, String password, final String email) {
		clientFactory.getStoreService().createAccount(name, email, password,
				new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						if(result == null){
							clientFactory.getPlaceController().goTo(new CreateAccountConfirmationPlace(name,email));
						}else{
							Window.alert(result);
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("(CreateAccountActivity) Erreur lors de la creation de l'utilisateur "
								+ caught);
					}
				});
	}

}
