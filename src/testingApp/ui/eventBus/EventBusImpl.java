package testingApp.ui.eventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Singleton;

@Singleton
public class EventBusImpl implements EventBus {

	private Map<Class<? extends Event>, List<EventListener<Event>>> refreshers = new HashMap<Class<? extends Event>, List<EventListener<Event>>>();

	@Override
	public <T extends Event> void publishEvent(Class<T> clazz, T event) {
		System.out.println("Publication d'un evenements " + event + " de type "
				+ clazz);

		List<EventListener<Event>> nogoodnameforit = this.refreshers.get(clazz);
		if (nogoodnameforit == null) {
			return;
		}

		for (EventListener<Event> listener : nogoodnameforit) {
			listener.onEvent(event);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Event> void registerEventListener(Class<T> clazz,
			EventListener<T> listener) {

		List<EventListener<Event>> nogoodnameforit = this.refreshers.get(clazz);
		if (nogoodnameforit == null) {
			nogoodnameforit = new ArrayList<EventListener<Event>>();
			refreshers.put(clazz, nogoodnameforit);
		}

		nogoodnameforit.add((EventListener<Event>) listener);
	}

}
