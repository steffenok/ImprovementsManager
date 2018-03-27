package de.divinesx.improvementsmanager.core.manager;

import javax.persistence.EntityManagerFactory;

import org.hibernate.cfg.Configuration;

import de.divinesx.improvementsmanager.core.Improvement;
import de.divinesx.improvementsmanager.core.ImprovementList;
import de.divinesx.improvementsmanager.core.entities.BugImprovement;
import lombok.Getter;

public class ImprovementManager {

	public static final ImprovementManager INSTANCE = new ImprovementManager();
	public static final Database DATABASE = new Database();

	private ImprovementManager() {}

	@Getter
	private ImprovementList improvements = new ImprovementList();
	
	public void addImprovement(Improvement improvent) { this.improvements.add(improvent); }
	
	public static class Database {
		
		@Getter
		private EntityManagerFactory entityFactory;
		@Getter
		private Configuration hibernateConfig;

		public Database openSessionFactory() {
			this.hibernateConfig = new Configuration().configure();
			this.hibernateConfig.addAnnotatedClass(BugImprovement.class);
			this.entityFactory = this.hibernateConfig.buildSessionFactory();
			return this;
		}

	}

}
