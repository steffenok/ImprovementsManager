package de.divinesx.improvementsmanager.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;

@ExtensionMethod(value = { Arrays.class })
@UtilityClass
public class EventManager {

	private final List<Listener> listeners = new ArrayList<Listener>();
	
	public void addListener(Listener listener) { listeners.add(listener); }
	
	@SneakyThrows
	public void callEvent(Event eventToCall) {
		for (Listener listener : listeners) {			
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
