package de.divinesx.improvementsmanager.core.entities;

import de.divinesx.improvementsmanager.core.Improvement;
import de.divinesx.improvementsmanager.core.ImprovementInfo;

@ImprovementInfo(type = Improvement.Type.WISH, priority = Improvement.Priority.LOW, tableName = "mgmt_Wishes")
public class WishImprovement extends Improvement {
}
