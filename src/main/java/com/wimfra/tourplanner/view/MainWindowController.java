package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.viewmodel.MainWindowViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    private AddLogController addLogController;
    @FXML
    private AddTourController addTourController;
    @FXML
    private EditLogController editLogController;
    @FXML
    private EditTourController editTourController;
    @FXML
    private LogViewController logViewController;
    @FXML
    private RouteController routeController;
    @FXML
    private TourListController tourListController;
    @FXML
    private ClickMeController clickMeController;

    private final MainWindowViewModel mainWindowViewModel;

    public MainWindowController(MainWindowViewModel mainWindowViewModel) {
        this.mainWindowViewModel = mainWindowViewModel;
    }

    public MainWindowViewModel getMainWindowViewModel() {
        return mainWindowViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
