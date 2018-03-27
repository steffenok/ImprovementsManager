package de.divinesx.improvementsmanager.core.events;

import de.divinesx.improvementsmanager.core.Improvement;
import de.divinesx.improvementsmanager.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ImprovementEditEvent implements Event {
	
	private Improvement improvement;
	
	private Object oldValue;
	
	private Object newValue;
	
	
}
