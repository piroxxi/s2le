package fr.piroxxi.s2le.client.asside;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.shared.model.UserStats;

public class AssideViewImpl extends Composite implements AssideView {
	interface MyUiBinder extends UiBinder<Widget, AssideViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	HTMLPanel createAccount;
	@UiField
	HTMLPanel userStatsPanel;
	@UiField
	UserStatsPanel userStats;
	
//	@UiField
//    Frame sponsor;

	private Delegate delegate;

	public AssideViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		userStatsPanel.setVisible(false);
	}

	@Override
	public void showUserStats(UserStats userStats) {
		userStatsPanel.setVisible(true);
		createAccount.setVisible(false);
		this.userStats.showUserStats(userStats);
	}

	@Override
	public void hideUserStats() {
		userStatsPanel.setVisible(false);
		createAccount.setVisible(true);
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
		userStats.setDelegate(delegate);
	}
	

	@UiHandler("create")
	public void logout(ClickEvent event) {
		if (delegate != null) {
			delegate.createAccount();
		}
	}
}
