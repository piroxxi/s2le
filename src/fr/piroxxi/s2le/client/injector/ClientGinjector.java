package fr.piroxxi.s2le.client.injector;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import fr.piroxxi.s2le.client.login.LoginActivity;

@GinModules(ClientModule.class)
public interface ClientGinjector extends Ginjector {
	
	LoginActivity getLoginActivity();
	EventBus getEventBus();
}
