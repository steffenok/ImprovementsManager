package de.divinesx.improvementsmanager;

import java.util.Calendar;

import javax.persistence.EntityManager;

import de.divinesx.improvementsmanager.core.ImprovementList;
import de.divinesx.improvementsmanager.core.entities.BugImprovement;
import de.divinesx.improvementsmanager.core.entities.NormalImprovement;
import de.divinesx.improvementsmanager.core.entities.WishImprovement;
import de.divinesx.improvementsmanager.core.manager.ImprovementManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ImproveMain extends Application {

	public static void main(String[] args) throws Exception {
		//ImprovementManager.DATABASE.openSessionFactory();
		
		doTestCompare();
		
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("resources/Main.fxml"));
	    
        Scene scene = new Scene(root, 500, 350);
    
        primaryStage.setTitle("ImprovementsManager");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void doTestTransaction() {
		EntityManager entityManager = ImprovementManager.DATABASE.getEntityFactory().createEntityManager();
		
		BugImprovement bug = new BugImprovement("UsageBug");
		
		entityManager.getTransaction().begin();
		entityManager.persist(bug);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	public static void doTestCompare() {
		ImprovementManager.INSTANCE.addImprovement(new NormalImprovement("NormalImprovement"));
		ImprovementManager.INSTANCE.addImprovement(new WishImprovement("WishImprovement"));
		ImprovementManager.INSTANCE.addImprovement(new BugImprovement("BugImprovement"));
		ImprovementManager.INSTANCE.getImprovements().get(0).setTimestamp(Calendar.getInstance());
		ImprovementManager.INSTANCE.getImprovements().get(1).setTimestamp(Calendar.getInstance());
		ImprovementManager.INSTANCE.getImprovements().get(2).setTimestamp(Calendar.getInstance());
		ImprovementManager.INSTANCE.getImprovements().getBy(ImprovementList.FilterType.DAY, "2018", "3", "27").forEach(i -> System.out.println(i.getPriority().toString()));
	}

}
