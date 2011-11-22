package fr.piroxxi.s2le.client.user.confirm;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CreateAccountConfirmationViewImpl extends Composite implements
		CreateAccountConfirmationView {
	interface MyUiBinder extends
			UiBinder<Widget, CreateAccountConfirmationViewImpl> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@SuppressWarnings("unused")
	private Delegate delegate;

	@UiField
	SpanElement nom;

	@UiField
	SpanElement email;

	public CreateAccountConfirmationViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void setInfos(String nom, String email) {
		this.nom.setInnerText(nom);
		this.email.setInnerText(email);
	}

}
