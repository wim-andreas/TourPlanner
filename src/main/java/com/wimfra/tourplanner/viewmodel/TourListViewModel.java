package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.TourPlannerApplication;
import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.dataaccesslayer.TourDAO;
import com.wimfra.tourplanner.models.Tour;
import com.wimfra.tourplanner.view.TourListController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.util.List;

public class TourListViewModel {
    // gets the connection to the business layer
    private JavaAppManager manager = JavaAppManagerFactory.GetManager();

    // different properties for bindings
    private final StringProperty currentSearchText = new SimpleStringProperty("");
    private final ObservableList<Tour> tourItems = FXCollections.observableArrayList();

    // getter and setter for the properties
    public StringProperty getCurrentSearchText(){
        return this.currentSearchText;
    }

    public List<Tour> getTourItems(){
        return manager.GetTours();
    }

    // different actions - communication with business and data access layer
    public List<Tour> searchAction() {
        tourItems.clear();
        List<Tour> items = manager.Search(currentSearchText.getValue(), false);
        return items;
    }

    public Tour getSingleTour(int id){
        return manager.GetSingleTour(id);
    }

    public void addNewTourWindow(){
        manager.AddTourWindow();
    }

    public void editTourWindow() { manager.EditTourWindow();
    }

    public void deleteTour(int tour_id) {
        manager.DeleteTour(tour_id);
    }
}
