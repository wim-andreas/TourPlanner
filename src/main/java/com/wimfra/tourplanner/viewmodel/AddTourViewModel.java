package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class AddTourViewModel {
    // creating the properties for the bidirectional binding
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty transportation = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty info = new SimpleStringProperty();


    // gets the connection to the business layer
    private JavaAppManager manager = JavaAppManagerFactory.GetManager();

    // access to the properties
    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty fromProperty() {
        return from;
    }
    public StringProperty toProperty() {
        return to;
    }
    public StringProperty transportationProperty() {
        return transportation;
    }
    public StringProperty durationProperty() {
        return duration;
    }
    public StringProperty distanceProperty() {
        return distance;
    }
    public StringProperty infoProperty() {
        return info;
    }

    public void addNewTour(List<String> data){
        manager.AddNewTour(data);
    }

}
