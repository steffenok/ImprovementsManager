package de.divinesx.improvementsmanager.core.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.divinesx.improvementsmanager.core.entities.BugImprovement;
import lombok.Getter;

public class ImprovementManager {
	
	private static ImprovementManager me;
	
	private ImprovementManager() {}
	
	public static ImprovementManager getMe() { if (me == null) me = new ImprovementManager(); return me; }

	@Getter
	private SessionFactory sessionFactory;
	@Getter
	private Configuration hibernateConfig;
	
	public void openSessionFactory() {
		this.hibernateConfig = new Configuration().configure();
		this.hibernateConfig.addAnnotatedClass(BugImprovement.class);
		
		this.sessionFactory = this.hibernateConfig.buildSessionFactory();
		
		Session session = this.sessionFactory.getCurrentSession();
		
		BugImprovement bug = new BugImprovement("TestBug23");
		session.beginTransaction();
		session.save(bug);
		session.getTransaction().commit();
	}
	
}
