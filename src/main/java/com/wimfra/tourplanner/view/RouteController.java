package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.mediator.*;
import com.wimfra.tourplanner.viewmodel.RouteViewModel;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.TabPane;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class RouteController implements Initializable {
    private RouteViewModel routeViewModel;
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(RouteController.class);
    private final Mediator mediator;
    public int tour_id;

    @FXML
    public Text description;
    @FXML
    public TabPane tabPane;

    public RouteController(RouteViewModel routeViewModel) {
        this.routeViewModel = routeViewModel;
        this.mediator = MediatorFactory.getMediator();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //creating the bidirectional databinding for the objects
        description.textProperty().bindBidirectional(routeViewModel.getDescriptionProperty());

        // setting events on the tab-pane
        tabPane.setOnMouseClicked(event -> loadDescription());
    }

    private void loadDescription() {
        routeViewModel.fetchCurrentDescription(mediator.getTourID());
    }

}
