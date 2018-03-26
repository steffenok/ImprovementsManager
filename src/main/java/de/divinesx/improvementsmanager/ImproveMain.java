package de.divinesx.improvementsmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ImproveMain extends Application {

	public static void main(String[] args) throws Exception {
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

}
