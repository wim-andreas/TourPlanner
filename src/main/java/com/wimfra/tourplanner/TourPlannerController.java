package com.wimfra.tourplanner;

import com.wimfra.tourplanner.controllers.AddTourController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class TourPlannerController {
    @FXML
    private ListView<String> TourList;
    private int i = 1;

    @FXML
    protected void AddTourOnButtonClick() throws IOException {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(TourPlannerApplication.class.getResource("add-tour-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 420, 240);
            stage.setTitle("TourPlanner v1.0");
            stage.setScene(scene);
            stage.show();

        }

    @FXML
    protected void DeleteTourOnButtonClick(){
        final int selectedIdx = TourList.getSelectionModel().getSelectedIndex();
        if(selectedIdx != -1) {
            TourList.getItems().remove(selectedIdx);
        }
        // ^ this should return players

    }
}