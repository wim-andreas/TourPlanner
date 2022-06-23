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
import javafx.scene.text.Text;

import java.util.List;

public class RouteViewModel {
    // gets the connection to the business layer
    private JavaAppManager manager = JavaAppManagerFactory.GetManager();
    private ManageTourService tourService = new ManageTourServiceImpl();

    // different properties for bindings
    public Text description;


    // getter and setter for the properties
    public List<Tour> getTourItems(){
        return tourService.getTours();
    }

    public Tour getSingleTour(int id){
        return tourService.getSingleTour(id);
    }

    // different actions - communication with business and data access layer

    public String getDescription(int id){
        Tour tour = getSingleTour(id);
        if(tour != null) {
            String description = "Tourname: " + tour.getTour_name() + "\n" +
                    "Description: " + tour.getDescription() + "\n" +
                    "From: " + tour.getFrom_where() + "\n" +
                    "To: " + tour.getTo_where() + "\n" +
                    "Transportation: " + tour.getTransportation() + "\n" +
                    "Distance: " + tour.getDistance() + "\n" +
                    "Duration: " + tour.getDuration() + "\n" +
                    "Info: " + tour.getRoute_info() + "\n";
            return description;
        }
        return null;

    }
}
