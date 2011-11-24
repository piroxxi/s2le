package fr.piroxxi.s2le.client.ui;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.piroxxi.s2le.client.ClientFactory;

public abstract class OperationCallback<T> implements AsyncCallback<T> {

	private final String error;
	private final ClientFactory clientFactory;

	public OperationCallback() {
		error = null;
		clientFactory = null;
	}

	public OperationCallback(ClientFactory clientFactory, String error) {
		this.clientFactory = clientFactory;
		this.error = error;
	}

	@Override
	public void onFailure(Throwable caught) {
		if (clientFactory != null) {
			clientFactory.getMessageBox().sendMessage(error, caught + "");
		}
	}

	@Override
	public abstract void onSuccess(T result);
}
