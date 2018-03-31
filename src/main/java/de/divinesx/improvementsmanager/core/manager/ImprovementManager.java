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
import lombok.Getter;
import lombok.Setter;

public class ImprovementManager {

	public static final ImprovementManager INSTANCE = new ImprovementManager();
	public static final Database DATABASE = new Database();

	private ImprovementManager() {}

	@Getter
	private ImprovementList improvements = new ImprovementList();
	

	@Getter @Setter
	private boolean showId = true;
	
	@Getter @Setter
	private boolean showDate = true;
	
	@Getter @Setter
	private boolean showPriority = true;
	
	public void addImprovement(Improvement improvent) { this.improvements.add(improvent); }
	
	public static class Database {
		
		@Getter
		private EntityManagerFactory entityFactory;
		
		@Getter
		private Configuration hibernateConfig;

		private List<Class<?>> annotatedClasses = Arrays.asList(Improvement.class, BugImprovement.class, NormalImprovement.class, WishImprovement.class);
		
		public Database openSessionFactory() {
			this.hibernateConfig = new Configuration().configure();
			this.annotatedClasses.forEach(this.hibernateConfig::addAnnotatedClass);
			this.entityFactory = this.hibernateConfig.buildSessionFactory();
			return this;
		}

	}

}
