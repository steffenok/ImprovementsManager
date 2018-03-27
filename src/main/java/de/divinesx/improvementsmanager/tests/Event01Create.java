package de.divinesx.improvementsmanager.tests;

import de.divinesx.improvementsmanager.core.events.ImprovementCreateEvent;
import de.divinesx.improvementsmanager.core.events.ImprovementEditEvent;
import de.divinesx.improvementsmanager.event.EventHandler;
import de.divinesx.improvementsmanager.event.Listener;

public class Event01Create implements Listener {
	
	@EventHandler
	public void onCreate(ImprovementCreateEvent event) {
		if (event.getImprovement().getName() != null)
		
		System.out.println("OnCreate: " + event.getImprovement().getName());
	}
	
	@EventHandler
	public void onEdit(ImprovementEditEvent event) {
		System.out.println("OnChange: " + event.getImprovement().getName());
		System.out.println("OnChange: " + event.getOldValue().toString() + ":" + event.getNewValue().toString());
	}
	
}
