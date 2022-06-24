package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.ManageTourLogService;
import com.wimfra.tourplanner.businesslayer.ManageTourLogServiceImpl;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.models.LogModel;
import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import com.wimfra.tourplanner.viewmodel.observerpattern.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class LogViewModel implements ViewModel {
    private final ManageTourLogService manageTourLogService = new ManageTourLogServiceImpl();
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(LogViewModel.class);
    private final ObservableList<LogModel> logItems = FXCollections.observableArrayList();
    private final StringProperty currentSearchText = new SimpleStringProperty();

    private Publisher publisher;

    public List<LogModel> getAllLogsFromDB() {
        return manageTourLogService.getAllLogs();
    }

    public void deleteLog(int logID) {
        manageTourLogService.deleteLog(logID);
    }

    public ObservableList<LogModel> getLogItems() {
        return logItems;
    }

    public void fetchLogItems() {
        logItems.clear();
        logItems.setAll(getAllLogsFromDB());
    }

    public void searchAction() {
        logItems.clear();
        List<LogModel> items = manageTourLogService.search(currentSearchText.getValue(), false);
        logItems.setAll(items);
    }


    // Observer pattern methods
    @Override
    public void updateFromDB() {
        fetchLogItems();
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public StringProperty getCurrentSearchText() {
        return this.currentSearchText;
    }
}
