package testingApp;

import com.google.inject.Binder;
import com.google.inject.Module;

public class SimpleTestingModule implements Module {

	public void configure(Binder binder) {
		binder.bind(fr.piroxxi.s2le.storage.api.Storage.class).to(
				fr.piroxxi.s2le.storage.fake.StorageImpl.class).asEagerSingleton();
	}

}
