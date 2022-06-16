package com.wimfra.tourplanner;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class TourPlannerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLDependencyInjection.load("main-view.fxml", Locale.GERMAN);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TourPlanner v1.0");
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}