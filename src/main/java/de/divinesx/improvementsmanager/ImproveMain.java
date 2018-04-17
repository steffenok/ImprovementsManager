package de.divinesx.improvementsmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import de.divinesx.improvementsmanager.core.ImprovementList;
import de.divinesx.improvementsmanager.core.entities.BugImprovement;
import de.divinesx.improvementsmanager.core.entities.NormalImprovement;
import de.divinesx.improvementsmanager.core.entities.WishImprovement;
import de.divinesx.improvementsmanager.core.manager.ImprovementManager;
import de.divinesx.improvementsmanager.event.EventManager;
import de.divinesx.improvementsmanager.tests.Event01Create;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ImproveMain extends Application {

	public static List<String> stringList = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception { launch(); }

	@Override
	public void start(Stage primaryStage) throws Exception {
		doTestCompare();
		
		Parent root = FXMLLoader.load(getClass().getResource("/resources/Main.fxml"));
	    
        Scene scene = new Scene(root, 480, 336);

        primaryStage.setTitle("ImprovementsManager");
        primaryStage.setScene(scene);
        
        primaryStage.setResizable(false);
        primaryStage.setMaxWidth(680);
        primaryStage.setMaxHeight(430);
        
        primaryStage.centerOnScreen();
        
        primaryStage.show();
        
		EventManager.addListener(new Event01Create());
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
		ImprovementManager.INSTANCE.addImprovement(new NormalImprovement("NormalImprovement2"));
		ImprovementManager.INSTANCE.addImprovement(new WishImprovement("WishImprovement"));
		ImprovementManager.INSTANCE.addImprovement(new BugImprovement("BugImprovement"));
		ImprovementManager.INSTANCE.addImprovement(new BugImprovement("BugImprovement2"));
	
		ImprovementManager.INSTANCE.getImprovements().get(0).setId(1);
		
		System.out.println();
		System.out.println("Printing sorted info:");
		ImprovementManager.INSTANCE.getImprovements().getBy(ImprovementList.FilterType.PRIORITY, "MAX").forEach(i -> System.out.println(i.getName() + ":" + i.getPriority().toString()));
	}

}
