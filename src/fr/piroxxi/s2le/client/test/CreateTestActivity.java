package fr.piroxxi.s2le.client.test;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.piroxxi.s2le.client.ClientFactory;
import fr.piroxxi.s2le.shared.model.Category;
import fr.piroxxi.s2le.shared.model.Difficulty;

public class CreateTestActivity extends AbstractActivity implements
		CreateTestView.Delegate {

	private ClientFactory factory;
	private CreateTestView view;

	public CreateTestActivity(ClientFactory factory) {
		this.factory = factory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.view = factory.getCreateTestView();
		view.setDelegate(this);
		panel.setWidget(view);
	}

	@Override
	public void startTest(List<Difficulty> difficulties,
			List<Category> categories, int quantity, boolean chronometree) {
//		this.factory.getStoreService().createTest("", difficulties, categories,
//				quantity, chronometree, new AsyncCallback<String>() {
//
//					@Override
//					public void onSuccess(String test) {
//						Window.alert("du coup on a bien le test " + test);
//						factory.getPlaceController().goTo(new TestPlace(test));
//					}
//
//					@Override
//					public void onFailure(Throwable caught) {
//						Window.alert("et merde! " + caught.getMessage());
//					}
//				});
	}

}
