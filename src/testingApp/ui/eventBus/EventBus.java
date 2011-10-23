package testingApp.ui.eventBus;


public interface EventBus {
	void publishEvent(Event event);
	void registerEventListener(EventListener<? extends Event> listener);
}
