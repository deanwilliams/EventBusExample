package test;

import com.google.common.eventbus.EventBus;

public class EventBusFactory {

	private static EventBus eventBus;
	
	public static EventBus getInstance() {
		if (eventBus == null) {
			eventBus = new EventBus();
		}
		return eventBus;
	}
}
