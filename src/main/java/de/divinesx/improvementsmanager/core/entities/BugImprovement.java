package de.divinesx.improvementsmanager.core.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.divinesx.improvementsmanager.core.Improvement;
import de.divinesx.improvementsmanager.core.ImprovementInfo;

@Entity
@Table(name = "mgmt_bugs")
@ImprovementInfo(type = Improvement.Type.BUG, priority = Improvement.Priority.MAX)
public class BugImprovement extends Improvement {

	public BugImprovement(String name) {
		super(name);
	}

}
