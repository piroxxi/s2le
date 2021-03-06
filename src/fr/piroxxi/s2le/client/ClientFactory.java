package fr.piroxxi.s2le.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

import fr.piroxxi.s2le.client.asside.AssideView;
import fr.piroxxi.s2le.client.asside.advise.AdvisorView;
import fr.piroxxi.s2le.client.hello.HelloView;
import fr.piroxxi.s2le.client.login.LoginView;
import fr.piroxxi.s2le.client.menu.MenuView;
import fr.piroxxi.s2le.client.messages.MessageBox;
import fr.piroxxi.s2le.client.messages.MessageView;
import fr.piroxxi.s2le.client.test.CreateTestView;
import fr.piroxxi.s2le.client.test.ResultTestView;
import fr.piroxxi.s2le.client.test.TestRunningView;
import fr.piroxxi.s2le.client.test.creation.CreateQuestionView;
import fr.piroxxi.s2le.client.test.liste.ListeQuestionsView;
import fr.piroxxi.s2le.client.ui.SessionManager;
import fr.piroxxi.s2le.client.user.confirm.CreateAccountConfirmationView;
import fr.piroxxi.s2le.client.user.create.CreateAccountView;
import fr.piroxxi.s2le.client.user.stats.UserStatistiquesView;
import fr.piroxxi.s2le.shared.StoreServiceAsync;
import fr.piroxxi.s2le.shared.security.SecurityServiceAsync;

public interface ClientFactory {
	EventBus getEventBus();

	PlaceController getPlaceController();
	
	MessageBox getMessageBox();

	// Services
	SecurityServiceAsync getSecurityService();

	StoreServiceAsync getStoreService();

	// View
	AssideView getAssideView();

	LoginView getLoginView();

	ListeQuestionsView getListeTestView();

	HelloView getHelloView();

	MenuView getMenuView();

	CreateTestView getCreateTestView();

	TestRunningView getTestRunningView();

	SessionManager getSessionManager();

	ResultTestView getResultTestView();

	AdvisorView getAdvisorView();

	CreateQuestionView getCreateQuestionView();

	CreateAccountView getCreateAccountView();

	CreateAccountConfirmationView getCreateAccountConfirmationView();

	UserStatistiquesView getUserStatistiquesView();

	MessageView getMessageView();

}
