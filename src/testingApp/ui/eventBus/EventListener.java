package testingApp.ui.eventBus;

public interface EventListener<E extends Event> {
	public void onEvent(E event);
}
