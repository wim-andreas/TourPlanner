package com.wimfra.tourplanner.view;


import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerImpl;
import com.wimfra.tourplanner.mediator.Mediator;
import com.wimfra.tourplanner.mediator.MediatorFactory;
import com.wimfra.tourplanner.models.LogModel;
import com.wimfra.tourplanner.models.Tour;
import com.wimfra.tourplanner.viewmodel.LogViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LogViewController implements Initializable {

    @FXML
    private TableColumn<LogModel, String> tourNameColumn;
    @FXML
    private TableColumn<LogModel, String> dateColumn;
    @FXML
    private TableColumn<LogModel, String> timeColumn;
    @FXML
    private TableColumn<LogModel, Integer> ratingColumn;
    @FXML
    private TableColumn<LogModel, String> difficultyColumn;
    @FXML
    private TableColumn<LogModel, String> commentColumn;
    @FXML
    private TableColumn<LogModel, String> totalTimeColumn;
    @FXML
    private TableView<LogModel> logData;
    @FXML
    private Button addLogBtn;
    @FXML
    private Button deleteLogBtn;
    @FXML
    private Button editLogBtn;


    private final LogViewModel logViewModel;
    private final JavaAppManager appManager = new JavaAppManagerImpl();
    private int logID;

    private ObservableList<LogModel> logItems;
    private final Mediator mediator;

    public LogViewController(LogViewModel logViewModel) {
        this.logViewModel = logViewModel;
        this.mediator = MediatorFactory.getMediator();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("Comment"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("Difficulty"));
        totalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("TotalTime"));
        tourNameColumn.setCellValueFactory(new PropertyValueFactory<>("TourName"));
        setUpLogView();

        addLogBtn.setOnAction(event -> addLogWindow());
        deleteLogBtn.setOnAction(event -> deleteLog());
        editLogBtn.setOnAction(event -> editLogWindow());
        logData.setOnMouseClicked(event-> setCurrentlySelectedTour());

    }

    private void editLogWindow() {
        setUpLogView();
        appManager.editLogWindow();
        setUpLogView();
    }

    private void deleteLog() {
            logViewModel.deleteLog(this.mediator.getLogID());
            setUpLogView();
    }

    public void setCurrentlySelectedTour() {
        if(logData.getSelectionModel().getSelectedItem() != null) {
            this.logID = logData.getSelectionModel().getSelectedItem().getLogID();
            this.mediator.setLogID(this.logID);
        }
    }

    private void setUpLogView(){
        logItems = FXCollections.observableArrayList();
        logItems.addAll(logViewModel.getAllLogs());
        logData.setItems(logItems);

    }

    private void addLogWindow(){
        appManager.addLogWindow();
    }

    
    }