package testingApp.ui.eventBus;

import com.google.inject.ImplementedBy;
import com.google.inject.Singleton;

@ImplementedBy(EventBusImpl.class)
public interface EventBus {
	<T extends Event> void registerEventListener(Class<T> clazz,
			EventListener<T> listener);

	<T extends Event> void publishEvent(Class<T> clazz, T event);
}
