package com.wimfra.tourplanner;

import com.wimfra.tourplanner.view.ControllerFactory;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Locale;

// FXMLLoader with Dependecy Injection

public class FXMLDependencyInjection {

    public static Parent load(String location, Locale locale) throws IOException {
        FXMLLoader loader = getLoader(location, locale);
        return loader.load();
    }

    private static FXMLLoader getLoader(String location, Locale locale) {
        return new FXMLLoader(
                FXMLDependencyInjection.class.getResource("/com/wimfra/tourplanner/" + location),
                null,
                new JavaFXBuilderFactory(),
                controllerClass -> ControllerFactory.getInstance().create(controllerClass)
        );
    }
}
