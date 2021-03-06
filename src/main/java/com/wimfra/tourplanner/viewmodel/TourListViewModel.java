package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.models.TourModel;
import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import com.wimfra.tourplanner.viewmodel.observerpattern.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TourListViewModel implements ViewModel {

    // gets the connection to the business layer
    private JavaAppManager appManager = JavaAppManagerFactory.GetManager();
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(TourListViewModel.class);
    private ManageTourService tourService = new ManageTourServiceImpl();
    private Publisher publisher;

    // different properties for bindings
    private final StringProperty currentSearchText = new SimpleStringProperty();
    private final ObservableList<TourModel> tourItems = FXCollections.observableArrayList();

    // getter and setter for the properties
    public StringProperty getCurrentSearchText() {
        return this.currentSearchText;
    }

    public void fetchTourItems() {
        tourItems.clear();
        tourItems.setAll(tourService.getTours());
    }

    public ObservableList<TourModel> getTourItems() {
        return tourItems;
    }

    // <---------------------------------------------------------------------------------------------------------------->

    // different actions - communication with business and data access layer
    public void searchAction() {
        tourItems.clear();
        List<TourModel> items = tourService.search(currentSearchText.getValue(), false);
        tourItems.setAll(items);
    }

    public TourModel getSingleTour(int id) {
        return tourService.getSingleTour(id);
    }

    public void addNewTourWindow() {
        appManager.addTourWindow();
    }

    public void editTourWindow(int tourID) {
        appManager.editTourWindow(tourID);
    }

    public void deleteTour(int tour_id) {
        tourService.deleteTour(tour_id);
        publisher.notifySubs();
        updateFromDB();
    }

    // Observer pattern methods
    @Override
    public void updateFromDB() {
        fetchTourItems();
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void updatePublisher() {
        publisher.notifySingleSubscriber("RouteViewModel");
    }
}
