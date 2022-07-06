package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerImpl;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.mediator.Mediator;
import com.wimfra.tourplanner.mediator.MediatorFactory;
import com.wimfra.tourplanner.models.LogModel;
import com.wimfra.tourplanner.viewmodel.LogViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LogViewController implements Initializable {

    private static final ILoggerWrapper logger = LoggerFactory.getLogger(LogViewController.class);
    @FXML
    public TextField logSearch;
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
    @FXML
    private Button searchBtn;
    @FXML
    private Button clearBtn;


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
        logSearch.textProperty().bindBidirectional(logViewModel.getCurrentSearchText());
        //setup table view
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
        logData.setOnMouseClicked(event -> setCurrentlySelectedTour());
        searchBtn.setOnAction(event -> searchAction());
        clearBtn.setOnAction(event -> clearAction());

    }

    private void editLogWindow() {
        setUpLogView();
        appManager.editLogWindow(this.mediator.getLogID());
        setUpLogView();
    }

    private void deleteLog() {
        logViewModel.deleteLog(this.mediator.getLogID());
        setUpLogView();
    }

    public void setCurrentlySelectedTour() {
        if (logData.getSelectionModel().getSelectedItem() != null) {
            this.logID = logData.getSelectionModel().getSelectedItem().getLogID();
            this.mediator.setLogID(this.logID);
        }
    }

    private void setUpLogView() {
        logViewModel.fetchLogItems();
        logData.setItems(logViewModel.getLogItems());
    }

    private void addLogWindow() {
        appManager.addLogWindow(this.mediator.getTourID());
    }

    public void searchAction() {
        logViewModel.searchAction();
    }

    public void clearAction() {
        logSearch.setText("");
        setUpLogView();
    }
}