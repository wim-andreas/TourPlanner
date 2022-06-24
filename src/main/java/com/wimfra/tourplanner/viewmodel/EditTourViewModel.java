package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.models.Tour;
import com.wimfra.tourplanner.view.TourListController;
import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import com.wimfra.tourplanner.viewmodel.observerpattern.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class EditTourViewModel implements ViewModel {
    // gets the connection to the business layer
    private JavaAppManager manager = JavaAppManagerFactory.GetManager();
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(EditTourViewModel.class);
    private ManageTourService tourService = new ManageTourServiceImpl();
    private Publisher publisher;

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

    public StringProperty descriptionProperty() {
        return description;
    }

    public Tour getSingleTour(int id) {
        return tourService.getSingleTour(id);
    }

    public void editTourData(List<String> data, int id) {
        tourService.editTourData(data, id);
        publisher.notifySubs();
    }

    // Observer pattern methods
    @Override
    public void updateFromDB() {

    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
