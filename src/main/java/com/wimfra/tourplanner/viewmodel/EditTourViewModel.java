package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;
import com.wimfra.tourplanner.models.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class EditTourViewModel {
    // gets the connection to the business layer
    private JavaAppManager manager = JavaAppManagerFactory.GetManager();
    private ManageTourService tourService = new ManageTourServiceImpl();

    // creating the properties for the bidirectional binding
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty transportation = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty info = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();

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
    public StringProperty descriptionProperty() { return description; }

    public Tour getSingleTour(int id) {
        return tourService.getSingleTour(id);
    }

    public void editTourData(List<String> data, int id) {
        tourService.editTourData(data, id);
    }
}
