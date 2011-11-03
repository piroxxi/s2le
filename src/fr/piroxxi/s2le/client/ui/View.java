package fr.piroxxi.s2le.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

public interface View<T extends View.Delegate> extends IsWidget {

	void setDelegate(T delegate);

	public interface Delegate {

	}
}
