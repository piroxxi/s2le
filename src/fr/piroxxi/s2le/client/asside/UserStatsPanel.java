package fr.piroxxi.s2le.client.asside;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.model.User;

public class UserStatsPanel extends Composite {
	public interface Delegate {
		void viewUser(User userStats);
	}

	interface MyUiBinder extends UiBinder<Widget, UserStatsPanel> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	Label userName;
	@UiField
	Label nbAnswer;
	@UiField
	Label nbGoodAnswer;

	private Delegate delegate;

	private User user;

	public UserStatsPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void showUserStats(User user) {
		this.user = user;
		userName.setText(user.getName());
		nbAnswer.setText(user.getNbQuestionsRepondues() + "");
		nbGoodAnswer.setText(user.getNbQuestionsJustes() + "");
	}

	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@UiHandler("more")
	public void logout(ClickEvent event) {
		if (delegate != null) {
			delegate.viewUser(user);
		}
	}
}
