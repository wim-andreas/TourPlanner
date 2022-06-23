package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.ManageTourLogServiceImpl;
import com.wimfra.tourplanner.models.LogModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class EditLogViewModel {
    private final StringProperty difficulty = new SimpleStringProperty();
    private final StringProperty rating = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty totalTime = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();

    private ManageTourLogServiceImpl manageTourLogService = new ManageTourLogServiceImpl();

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
}
