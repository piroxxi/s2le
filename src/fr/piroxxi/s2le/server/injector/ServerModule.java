package fr.piroxxi.s2le.server.injector;

import com.google.inject.Binder;
import com.google.inject.Module;

public class ServerModule implements Module {

	public void configure(Binder binder) {
		binder.bind(fr.piroxxi.s2le.storage.api.Storage.class).to(fr.piroxxi.s2le.storage.fake.StorageImpl.class);
	}

}
