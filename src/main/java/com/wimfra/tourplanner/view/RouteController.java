package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.businesslayer.mapquest.MapQuestAPI;
import com.wimfra.tourplanner.dataaccesslayer.DBService;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.mediator.*;
import com.wimfra.tourplanner.models.TourModel;
import com.wimfra.tourplanner.viewmodel.RouteViewModel;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.image.Image;

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
    @FXML
    public ImageView currentTourImage;

    public RouteController(RouteViewModel routeViewModel) {
        this.routeViewModel = routeViewModel;
        this.mediator = MediatorFactory.getMediator();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.debug("Setting up 'route-view.fxml'");
        //creating the bidirectional databinding for the objects
        description.textProperty().bindBidirectional(routeViewModel.getDescriptionProperty());
        currentTourImage.imageProperty().bindBidirectional(routeViewModel.getImageProperty());

        // setting events on the tab-pane
        tabPane.setOnMouseClicked(event -> loadDescription());
        tabPane.setOnMouseClicked(event -> loadImage());
    }

    private void loadDescription() {
        routeViewModel.fetchCurrentDescription(mediator.getTourID());
    }

    public void loadImage()  {
        routeViewModel.fetchImage(mediator.getTourID());
    }

}



