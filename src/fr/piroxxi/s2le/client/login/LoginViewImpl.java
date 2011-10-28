package fr.piroxxi.s2le.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginViewImpl extends Composite implements LoginView {

	/*
	 * Là, c'est un poil tricky, on créer un interface qui est un UiBinder, qui
	 * va faire le lien entre le fichier LoginViewImpl.ui.xml, et cette classe
	 * ici. Ensuite on en crée une instance, et sur cette instance on pourra
	 * faire un uiBinder.createAndBindUi(this) qui nous renvera un "Composite"
	 * (c'est à dire un Panel (au sens large)) qui contiendra tout ce qu'on
	 * veut. (tout ce qu'on aura defini dans le ui.xml).
	 */
	interface MyUiBinder extends UiBinder<Widget, LoginViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	/*
	 * Délégué, par lequel on dit ce qu'il se passe à l'activité.
	 */
	private Delegate delegate;

	@UiField
	TextBox name;
	@UiField
	PasswordTextBox password;

	@UiField
	Label userName;

	@UiField
	HTMLPanel connectedPanel;

	@UiField
	HTMLPanel deconnectedPanel;

	public LoginViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		this.connectedPanel.setVisible(true);
		this.deconnectedPanel.setVisible(false);
	}

	@Override
	public void setConnectedUser(String userName) {
		if (userName != null) {
			this.connectedPanel.setVisible(false);
			this.deconnectedPanel.setVisible(true);
			this.userName.setText(userName);
		} else {
			this.connectedPanel.setVisible(true);
			this.deconnectedPanel.setVisible(false);
		}
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@UiHandler("login")
	public void login(ClickEvent event) {
		if (delegate != null) {
			delegate.login(name.getText(), password.getText());
		}
	}

	@UiHandler("logout")
	public void logout(ClickEvent event) {
		if (delegate != null) {
			delegate.logout();
		}
	}
}
