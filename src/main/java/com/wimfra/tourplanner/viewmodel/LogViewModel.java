package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.ManageTourLogService;
import com.wimfra.tourplanner.businesslayer.ManageTourLogServiceImpl;
import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.models.LogModel;
import com.wimfra.tourplanner.models.Tour;
import com.wimfra.tourplanner.view.TourListController;
import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import com.wimfra.tourplanner.viewmodel.observerpattern.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class LogViewModel implements ViewModel {
    private final ManageTourLogService manageTourLogService = new ManageTourLogServiceImpl();
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(LogViewModel.class);
    private final ObservableList<LogModel> logItems = FXCollections.observableArrayList();
    private Publisher publisher;

    public List<LogModel> getAllLogs() {
        return manageTourLogService.getAllLogs();
    }

    public void deleteLog(int logID) {
        manageTourLogService.deleteLog(logID);
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
