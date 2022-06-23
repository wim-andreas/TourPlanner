package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.TourPlannerApplication;
import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.mediator.*;
import com.wimfra.tourplanner.models.Tour;
import com.wimfra.tourplanner.viewmodel.RouteViewModel;
import com.wimfra.tourplanner.viewmodel.TourListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Tab;

import javafx.scene.text.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class RouteController implements Initializable {
    private RouteViewModel routeViewModel;



    public int tour_id;

    @FXML
    public Text description;


    public RouteController(RouteViewModel routeViewModel) {
        this.routeViewModel = routeViewModel;

    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDescription();
    }

    private void loadDescription() {
       // description.setText(routeViewModel.getDescription(controllerMediator.getTourID()));


    }


}
