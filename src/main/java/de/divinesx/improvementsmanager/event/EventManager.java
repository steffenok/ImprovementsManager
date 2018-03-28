package de.divinesx.improvementsmanager.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

	public static final EventManager INSTANCE = new EventManager();
	
	private final List<Listener> listeners = new ArrayList<Listener>();
	
	private EventManager() {}
	
	public void addListener(Listener listener) { this.listeners.add(listener); }
	
	public void callEvent(Event eventToCall) {
		
		for (Listener listener : this.listeners) {			
			for (Method method : listener.getClass().getDeclaredMethods()) {
				method.setAccessible(true);
				
				if (!method.isAnnotationPresent(EventHandler.class)) continue;
				if (method.getParameterCount() != 1) continue;
				if (method.getParameterTypes()[0] != eventToCall.getClass()) continue;
				
				try {
					method.invoke(listener, eventToCall);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
	}
	
}
