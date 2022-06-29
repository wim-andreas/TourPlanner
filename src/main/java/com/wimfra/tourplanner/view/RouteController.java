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



import java.awt.*;
import java.io.*;
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

    private DBService dbService;

    public RouteController(RouteViewModel routeViewModel) {
        this.routeViewModel = routeViewModel;
        this.mediator = MediatorFactory.getMediator();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //creating the bidirectional databinding for the objects
        description.textProperty().bindBidirectional(routeViewModel.getDescriptionProperty());
        dbService = DBService.getInstance();


        // setting events on the tab-pane
        tabPane.setOnMouseClicked(event -> loadDescription());
        tabPane.setOnMouseClicked(event ->


                createImage());


    }

    private void createImage() {
        int cur_id = mediator.getTourID();
        TourModel tour = dbService.getSingleTour(cur_id);
        File dir = new File("./src/main/resources/images/");

        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.equals(cur_id + ".jpg");
            }
        };
        String[] files = dir.list(filter);
        if (files == null) {
            System.out.println("Either dir does not exist or is not a directory");
        } else {
            if (files.length == 0) {
                System.out.println("File not found");
                routeViewModel.createTourImage(tour.getFrom_where(), tour.getTo_where(), cur_id);
            }

        }
        loadImage();
    }


    private void loadDescription() {
        routeViewModel.fetchCurrentDescription(mediator.getTourID());
    }

    public void loadImage()  {
        int cur_id = mediator.getTourID();
        Image image =  new Image(getClass().getResourceAsStream("/images/" +cur_id + ".jpg"));
        currentTourImage.setImage(image);
    }

}



