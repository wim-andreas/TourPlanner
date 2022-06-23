package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.ManageTourLogService;
import com.wimfra.tourplanner.businesslayer.ManageTourLogServiceImpl;
import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;
import com.wimfra.tourplanner.models.LogModel;
import com.wimfra.tourplanner.models.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class LogViewModel {
    private final ManageTourLogService manageTourLogService = new ManageTourLogServiceImpl();
    private final ObservableList<LogModel> logItems = FXCollections.observableArrayList();

    public List<LogModel> getAllLogs() {
        return manageTourLogService.getAllLogs();
    }
}
