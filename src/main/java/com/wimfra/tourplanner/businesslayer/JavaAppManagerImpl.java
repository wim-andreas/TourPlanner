package com.wimfra.tourplanner.businesslayer;



import com.wimfra.tourplanner.FXMLDependencyInjection;
import com.wimfra.tourplanner.TourPlannerApplication;
import com.wimfra.tourplanner.dataaccesslayer.TourDAO;
import com.wimfra.tourplanner.models.Tour;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class JavaAppManagerImpl implements JavaAppManager{
    private TourDAO tourDAO = new TourDAO();

    @Override
    public void addTourWindow() {
        Scene scene = null;
        Stage stage = new Stage();
        try {
            scene = new Scene(FXMLDependencyInjection.load("add-tour-view.fxml", Locale.GERMAN));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Add a new tour");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void editTourWindow() {
        Scene scene = null;
        Stage stage = new Stage();
        try {
            scene = new Scene(FXMLDependencyInjection.load("edit-tour-view.fxml", Locale.GERMAN));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Edit a tour");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void openLogWindow() {
        Scene scene = null;
        Stage stage = new Stage();
        try {
            scene = new Scene(FXMLDependencyInjection.load("add-log-view.fxml", Locale.GERMAN));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Add a new tour-log");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }


}
