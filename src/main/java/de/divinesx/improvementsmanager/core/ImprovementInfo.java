package de.divinesx.improvementsmanager.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention( RetentionPolicy.RUNTIME )
@Target(ElementType.TYPE)
public @interface ImprovementInfo {
	
	Improvement.Type type();
	Improvement.Priority priority();
	String tableName();
	
}
