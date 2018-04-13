package de.divinesx.improvementsmanager.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;

public class EventManager {

	public static final EventManager INSTANCE = new EventManager();
	
	private final List<Listener> listeners = new ArrayList<Listener>();
	
	private EventManager() {}
	
	public void addListener(Listener listener) { this.listeners.add(listener); }
	
	@SneakyThrows
	public void callEvent(Event eventToCall) {
		
		for (Listener listener : this.listeners) {			
			for (Method method : listener.getClass().getDeclaredMethods()) {
				method.setAccessible(true);
				
				if (!method.isAnnotationPresent(EventHandler.class)) continue;
				if (method.getParameterCount() != 1) continue;
				if (method.getParameterTypes()[0] != eventToCall.getClass()) continue;
				
				method.invoke(listener, eventToCall);
			}
		}
		
	}
	
}
