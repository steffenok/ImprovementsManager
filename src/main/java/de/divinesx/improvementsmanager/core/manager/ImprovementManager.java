package de.divinesx.improvementsmanager.core.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.cfg.Configuration;

import de.divinesx.improvementsmanager.core.Improvement;
import de.divinesx.improvementsmanager.core.ImprovementList;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.FieldDefaults;

@ExtensionMethod(Arrays.class)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImprovementManager {

	public static final ImprovementManager INSTANCE = new ImprovementManager();
	public static final Database DATABASE = new Database();

	ImprovementManager() {
	}

	final ImprovementList improvements = new ImprovementList();

	boolean showId = true;

	boolean showDate = true;

	boolean showPriority = true;

	public void addImprovement(Improvement improvent) {
		this.improvements.add(improvent);
	}

	@Getter
	@FieldDefaults(level = AccessLevel.PRIVATE)
	public static class Database {

		EntityManagerFactory entityFactory;

		Configuration hibernateConfig;

		@Getter(value = AccessLevel.PRIVATE)
		List<Class<?>> annotatedClasses = this.getClasses("de.divinesx.improvementsmanager.core.entities", Improvement.class);

		public Database openSessionFactory() {
			this.hibernateConfig = new Configuration().configure();
			this.annotatedClasses.forEach(this.hibernateConfig::addAnnotatedClass);
			this.entityFactory = this.hibernateConfig.buildSessionFactory();
			return this;
		}

		@SneakyThrows
		public List<Class<?>> getClasses(String packageName, Class<?>... additionalClasses) {
			ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
			File directory = new File(Thread.currentThread().getContextClassLoader().getResource(packageName.replace('.', '/')).getFile());

			if (!directory.exists()) return null;
			
			String[] files = directory.list();
			for (String fileName : files)
				if (fileName.endsWith(".class"))
					classes.add(Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6)));

			additionalClasses.stream().forEach(classes::add);
			return classes;
		}

	}

}
