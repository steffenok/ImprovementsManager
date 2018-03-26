package de.divinesx.improvementsmanager.core.entities;

import de.divinesx.improvementsmanager.core.Improvement;
import de.divinesx.improvementsmanager.core.ImprovementInfo;

import javax.persistence.Table;


@Table(name = "mgmt_Bugs")
@ImprovementInfo(type = Improvement.Type.BUG, priority = Improvement.Priority.MAX, tableName = "mgmt_Bugs")
public class BugImprovement extends Improvement {
	
	public BugImprovement( String name ) {
		super( name );
	}
	
}
