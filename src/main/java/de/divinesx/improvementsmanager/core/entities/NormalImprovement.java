package de.divinesx.improvementsmanager.core.entities;

import de.divinesx.improvementsmanager.core.Improvement;
import de.divinesx.improvementsmanager.core.ImprovementInfo;

@ImprovementInfo(type = Improvement.Type.NORMAL, priority = Improvement.Priority.MIDDLE)
public class NormalImprovement extends Improvement {
	
	public NormalImprovement(String name) {
		super(name);
	}
	
}
