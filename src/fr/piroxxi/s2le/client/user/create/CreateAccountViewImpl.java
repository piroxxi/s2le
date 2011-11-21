package fr.piroxxi.s2le.client.user.create;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.piroxxi.s2le.client.ui.LigneFormulaire;

public class CreateAccountViewImpl extends Composite implements
		CreateAccountView {
	interface MyUiBinder extends UiBinder<Widget, CreateAccountViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	private Delegate delegate;

	@UiField
	TextBox nom;
	@UiField
	LigneFormulaire ligne_nom;

	@UiField
	TextBox email;
	@UiField
	LigneFormulaire ligne_email;

	@UiField
	PasswordTextBox password1;
	@UiField
	LigneFormulaire ligne_password1;

	@UiField
	PasswordTextBox password2;
	@UiField
	LigneFormulaire ligne_password2;

	@UiField
	CheckBox conditions;
	@UiField
	LigneFormulaire ligne_conditions;

	public CreateAccountViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@UiHandler("create")
	public void create(ClickEvent clickEvent) {
		if (this.delegate != null) {
			boolean hasErrors = false;
			/*
			 * Verification de l'acceptation des conditions générales
			 * d'utilisation.
			 */
			if (!conditions.getValue()) {
				ligne_conditions
						.setError("Veuillez accepter les conditions générales d'utilisation.");
				hasErrors = true;
			} else {
				ligne_conditions.setError(null);
			}

			/*
			 * Verification du nom.
			 */
			if (nom.getValue().isEmpty()) {
				ligne_nom.setError("Veuillez entrer un nom.");
				hasErrors = true;
			} else if (nom.getValue().length() < 4) {
				ligne_nom
						.setError("Le nom que vous avez choisi est trop court.");
				hasErrors = true;
			} else {
				ligne_nom.setError(null);
			}

			/*
			 * Verification de l'email.
			 */
			String email = this.email.getValue();
			if (email.isEmpty()) {
				ligne_email
						.setError("Veuillez entrer une adresse de courrier électronique.");
				hasErrors = true;
				// TODO mettre une regex.
			} else if (!email.contains("@") || email.endsWith(".")
					|| email.startsWith(".")
					|| email.substring(email.indexOf('@')).isEmpty()) {
				ligne_email
						.setError("Veuillez entrer une adresse valide (de la forme <nom>@<adresse>.<pays>).");
				hasErrors = true;
			} else {
				ligne_email.setError(null);
			}

			/*
			 * Verification du mot de passe.
			 */
			if (password1.getValue().isEmpty()) {
				ligne_password1.setError("Veuillez entrer un mot de passe.");
				hasErrors = true;
			} else if (password1.getValue().length() < 6) {
				ligne_password1
						.setError("Veuillez entrer un mot de passe d'au moins 6 caractères.");
				hasErrors = true;
			} else {
				ligne_password1.setError(null);
			}

			if (!password1.getValue().equals(password2.getValue())) {
				ligne_password2
						.setError("Les deux mots de passes doivent être identiques.");
				hasErrors = true;
			} else {
				ligne_password2.setError(null);
			}

			if (!hasErrors) {
				this.delegate.createAccount(this.nom.getValue(),
						this.password1.getValue(), this.email.getValue());
			}
		}
	}
}
