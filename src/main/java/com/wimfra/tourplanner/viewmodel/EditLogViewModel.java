package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.ManageTourLogServiceImpl;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.models.LogModel;
import com.wimfra.tourplanner.view.TourListController;
import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import com.wimfra.tourplanner.viewmodel.observerpattern.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class EditLogViewModel implements ViewModel {
    private final StringProperty difficulty = new SimpleStringProperty();
    private final StringProperty rating = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty totalTime = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();

    private ManageTourLogServiceImpl manageTourLogService = new ManageTourLogServiceImpl();
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(EditLogViewModel.class);
    private Publisher publisher;

    public StringProperty difficultyProperty() { return difficulty;}

    public StringProperty ratingProperty() {return rating;}

    public StringProperty dateProperty() { return date;}

    public StringProperty timeProperty() { return time;}

    public StringProperty totalTimeProperty() { return totalTime;}

    public StringProperty commentProperty() { return comment;}

    public List<String> getSingleLog(int logID) {
        return manageTourLogService.getSingleLog(logID);
    }

    public void editLogData(List<String> data, int logID) {
        manageTourLogService.editLogData(data, logID);
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
