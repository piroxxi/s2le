package fr.piroxxi.s2le.client.test;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.client.places.TestPlace;
import fr.piroxxi.s2le.client.ui.OperationCallback;
import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;

public class CreateTestActivity extends AbstractActivity implements
		CreateTestView.Delegate {

	private ClientFactory factory;
	private CreateTestView view;

	public CreateTestActivity(ClientFactory factory) {
		this.factory = factory;
		this.view = factory.getCreateTestView();
		view.setDelegate(this);

		this.factory
				.getStoreService()
				.getCategories(
						new OperationCallback<Category[]>(this.factory,
								"(CreateTestActivity) Erreure lors de la récupération des catégories.") {
							@Override
							public void onSuccess(Category[] result) {
								view.setCategories(result);
							}
						});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

	@Override
	public void startTest(List<Difficulty> difficulties,
			List<Category> categories, int quantity, boolean chronometree) {

		this.factory
				.getStoreService()
				.createTest(
						this.factory.getSessionManager().getSessionId(),
						difficulties,
						categories,
						quantity,
						chronometree,
						new OperationCallback<String>(this.factory,
								"(CreateTestActivity) Erreure lors de la creation du test") {
							@Override
							public void onSuccess(String test) {
								if (test == null) {
									Window.alert("Impossible de trouver un test avec les paramètres fournis.");
								} else {
									factory.getPlaceController().goTo(
											new TestPlace(test));
								}
							}
						});
	}

}
