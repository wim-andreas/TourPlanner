package com.wimfra.tourplanner.view;


import com.wimfra.tourplanner.models.LogModel;
import com.wimfra.tourplanner.models.Tour;
import com.wimfra.tourplanner.viewmodel.LogViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<LogModel> tbData;

    private final LogViewModel logViewModel;

    private ObservableList<LogModel> logItems;

    public LogViewController(LogViewModel logViewModel) {
        this.logViewModel = logViewModel;
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

    }

    private void setUpLogView(){
        logItems = FXCollections.observableArrayList();
        logItems.addAll(logViewModel.getAllLogs());
        tbData.setItems(logItems);

    }





    }