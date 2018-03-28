package de.divinesx.improvementsmanager.ui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;

import de.divinesx.improvementsmanager.core.Improvement;
import de.divinesx.improvementsmanager.core.ImprovementList.FilterType;
import de.divinesx.improvementsmanager.core.entities.WishImprovement;
import de.divinesx.improvementsmanager.core.manager.ImprovementManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class MainController implements Initializable {
	
	@FXML
	private JFXButton settingsButton;
	@FXML
	private JFXListView<Improvement> improvementList;
	@FXML
	private JFXSlider showFilter;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.initializeSettingsButton();
		this.initializeImprovementsList();
		this.setupSliderFilter();
	}
	
	private void initializeSettingsButton() {
		double[] preferedSize = new double[] { this.settingsButton.getMaxWidth() - 3, this.settingsButton.getMaxHeight() - 3 };
		ImageView imageView = new ImageView(new Image("resources/images/button_settings.png"));
		imageView.setFitWidth(preferedSize[0]);
		imageView.setFitHeight(preferedSize[1]);
		this.settingsButton.setGraphic(imageView);
		this.settingsButton.getGraphic().setTranslateX(-6);
		this.settingsButton.setOnAction(onAction -> {
			ImprovementManager.INSTANCE.addImprovement(new WishImprovement("LOL"));
			this.callListUpdate(this.showFilter.getValue());
		});
	}
	
	private void initializeImprovementsList() {
		this.improvementList.setItems(ImprovementManager.INSTANCE.getImprovements());
		
		this.improvementList.setCellFactory(callback -> {
			return new ListCell<Improvement>() {
				@Override
				protected void updateItem(Improvement improvement, boolean empty) {
					super.updateItem(improvement, empty);
					if (empty) setGraphic(null);
					if (improvement == null) return;

					VBox vBox = new VBox(new Text(improvement.getName()), new Text(improvement.getPriority().toString()));
                    HBox hBox = new HBox(improvement.getImageView(), vBox);
                    hBox.setSpacing(10);
                    setGraphic(hBox);
				}
			};
		});
	}
	
	private void setupSliderFilter() {
		this.showFilter.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n < 0.5) return "All";
                if (n < 1.5) return "Priority";
                
				return "All";
            }

            @Override
            public Double fromString(String s) {
                if (s.equalsIgnoreCase("All")) return 0D;
                else if (s.equalsIgnoreCase("Priority")) return 1D;
                else return 0D;
            }
        }); 
		
		this.showFilter.valueProperty().addListener((ov, oldValue, newValue) -> { this.callListUpdate(newValue); });
	}
	
	private void callListUpdate(Number value) {
		if (value.intValue() == 0) {
    		improvementList.setItems(ImprovementManager.INSTANCE.getImprovements());
    	}
      	else if (value.intValue() == 1) {
    		improvementList.setItems(ImprovementManager.INSTANCE.getImprovements().getBy(FilterType.PRIORITY, "LOW"));
       	}
	}

}
