package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.viewmodel.MainWindowViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML private AddTourController addTourController;
    @FXML private RouteController routeController;
    @FXML private TourListController tourListController;
    @FXML private AddTourController editTourController;

    @FXML
    public Button addLogBtn;

    private final MainWindowViewModel mainWindowViewModel;

    public MainWindowController(MainWindowViewModel mainWindowViewModel) {
        this.mainWindowViewModel = mainWindowViewModel;
    }

    public MainWindowViewModel getMainWindowViewModel() {
        return mainWindowViewModel;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addLogBtn.setOnAction(event->openLogWindow());
    }

    public void openLogWindow(){
        mainWindowViewModel.openLogWindow();
    }


}
