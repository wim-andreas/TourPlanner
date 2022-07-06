package com.wimfra.tourplanner.businesslayer;


import com.wimfra.tourplanner.FXMLDependencyInjection;
import com.wimfra.tourplanner.dataaccesslayer.LogDAO;
import com.wimfra.tourplanner.dataaccesslayer.TourDAO;
import com.wimfra.tourplanner.models.LogModel;
import com.wimfra.tourplanner.models.TourModel;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class JavaAppManagerImpl implements JavaAppManager {
    private TourDAO tourDAO = new TourDAO();
    private LogDAO logDAO = new LogDAO();

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
    public void editTourWindow(int tourID) {
        TourModel tour = tourDAO.GetSingleTour(tourID);
        Scene scene = null;
        Stage stage = new Stage();
        try {
            scene = new Scene(FXMLDependencyInjection.load("edit-tour-view.fxml", Locale.GERMAN));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Edit: " +tour.getTour_name());
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void addLogWindow(int tourID) {
        TourModel tour = tourDAO.GetSingleTour(tourID);
        Scene scene = null;
        Stage stage = new Stage();
        try {
            scene = new Scene(FXMLDependencyInjection.load("add-log-view.fxml", Locale.GERMAN));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Add a log for "+ tour.getTour_name() + "-tour");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    @Override
    public void editLogWindow(int logID) {
        List<String> log = logDAO.GetSingleLog(logID);
        TourModel tour = tourDAO.GetSingleTour(Integer.parseInt(log.get(6)));

        Scene scene = null;
        Stage stage = new Stage();
        try {
            scene = new Scene(FXMLDependencyInjection.load("edit-log-view.fxml", Locale.GERMAN));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Edit "+ tour.getTour_name()+"-log:");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


}
