package de.divinesx.improvementsmanager.core.manager;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.cfg.Configuration;

import de.divinesx.improvementsmanager.core.Improvement;
import de.divinesx.improvementsmanager.core.ImprovementList;
import de.divinesx.improvementsmanager.core.entities.BugImprovement;
import de.divinesx.improvementsmanager.core.entities.NormalImprovement;
import de.divinesx.improvementsmanager.core.entities.WishImprovement;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE)
public class ImprovementManager {

	public static final ImprovementManager INSTANCE = new ImprovementManager();
	public static final Database DATABASE = new Database();

	ImprovementManager() {}

	final ImprovementList improvements = new ImprovementList();
	
	boolean showId = true;
	
	boolean showDate = true;
	
	boolean showPriority = true;
	
	public void addImprovement(Improvement improvent) { this.improvements.add(improvent); }
	
	@Getter @FieldDefaults(level = AccessLevel.PRIVATE)
	public static class Database {
		
		EntityManagerFactory entityFactory;
		
		Configuration hibernateConfig;

		@Getter(value = AccessLevel.PRIVATE)
		List<Class<?>> annotatedClasses = Arrays.asList(Improvement.class, BugImprovement.class, NormalImprovement.class, WishImprovement.class);
		
		public Database openSessionFactory() {
			this.hibernateConfig = new Configuration().configure();
			this.annotatedClasses.forEach(this.hibernateConfig::addAnnotatedClass);
			this.entityFactory = this.hibernateConfig.buildSessionFactory();
			return this;
		}

	}

}
