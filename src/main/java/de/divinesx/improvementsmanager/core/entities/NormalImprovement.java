package de.divinesx.improvementsmanager.core.entities;

import de.divinesx.improvementsmanager.core.Improvement;
import de.divinesx.improvementsmanager.core.ImprovementInfo;
import javafx.scene.image.Image;

@ImprovementInfo(type = Improvement.Type.NORMAL, priority = Improvement.Priority.MIDDLE)
public class NormalImprovement extends Improvement {
	
	public NormalImprovement(String name) {
		super(name);
		this.setDisplayImage(new Image("/resources/images/improvement_normal.png"));
	}
	
}
