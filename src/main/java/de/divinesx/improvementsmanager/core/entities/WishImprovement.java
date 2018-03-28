package de.divinesx.improvementsmanager.core.entities;

import de.divinesx.improvementsmanager.core.Improvement;
import de.divinesx.improvementsmanager.core.ImprovementInfo;
import javafx.scene.image.Image;

@ImprovementInfo(type = Improvement.Type.WISH, priority = Improvement.Priority.LOW)
public class WishImprovement extends Improvement {
	
	public WishImprovement(String name) {
		super(name);
		this.setDisplayImage(new Image("/resources/images/improvement_wish.png"));
	}
	
}
