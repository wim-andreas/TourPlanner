package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;
import com.wimfra.tourplanner.models.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TourListViewModel {
    // gets the connection to the business layer
    private JavaAppManager appManager = JavaAppManagerFactory.GetManager();
    private ManageTourService tourService = new ManageTourServiceImpl();

    // different properties for bindings
    private final StringProperty currentSearchText = new SimpleStringProperty();
    private final ObservableList<Tour> tourItems = FXCollections.observableArrayList();

    // getter and setter for the properties
    public StringProperty getCurrentSearchText(){
        return this.currentSearchText;
    }
    public List<Tour> getTourItems(){
        return tourService.getTours();
    }

    // different actions - communication with business and data access layer
    public List<Tour> searchAction() {
        tourItems.clear();
        List<Tour> items = tourService.search(currentSearchText.getValue(), false);
        return items;
    }

    public Tour getSingleTour(int id){
        return tourService.getSingleTour(id);
    }

    public void addNewTourWindow(){
        appManager.addTourWindow();
    }

    public void editTourWindow() { appManager.editTourWindow();
    }

    public void deleteTour(int tour_id) {
        tourService.deleteTour(tour_id);
    }
}
