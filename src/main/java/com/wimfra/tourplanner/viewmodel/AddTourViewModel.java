package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;
import com.wimfra.tourplanner.businesslayer.parsing.ParserService;
import com.wimfra.tourplanner.businesslayer.parsing.ParserServiceImpl;
import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import com.wimfra.tourplanner.viewmodel.observerpattern.ViewModel;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class AddTourViewModel implements ViewModel {
    // creating the properties for the bidirectional binding
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty transportation = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty info = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();


    // gets the connection to the business layer
    private JavaAppManager manager = JavaAppManagerFactory.GetManager();
    private ManageTourService tourService = new ManageTourServiceImpl();
    private ParserService parserService = new ParserServiceImpl();
    private Publisher publisher;

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

    public void addNewTour(){
        List<String> data = new ArrayList();
        data.add(0, nameProperty().get());
        data.add(1, descriptionProperty().get());
        data.add(2, fromProperty().get());
        data.add(3, toProperty().get());
        data.add(4, transportationProperty().get());
        data.add(5, distanceProperty().get());
        data.add(6, durationProperty().get());
        data.add(7, infoProperty().get());
        tourService.addNewTour(data);
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
