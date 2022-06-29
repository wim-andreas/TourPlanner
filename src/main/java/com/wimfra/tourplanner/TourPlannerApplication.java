package com.wimfra.tourplanner;

import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class TourPlannerApplication extends Application {
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(TourPlannerApplication.class);

    @Override
    public void start(Stage stage) throws IOException {
        logger.debug("Program is starting");
        logger.debug("Loading stage...");
        Parent root = FXMLDependencyInjection.load("main-view.fxml", Locale.GERMAN);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TourPlanner v1.0");
        stage.show();
        logger.debug("Loading completed!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}