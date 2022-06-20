package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.viewmodel.AddLogViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddLogController implements Initializable {

    private final AddLogViewModel addLogViewModel;

    @FXML
    public ChoiceBox difficultyChoiceBox;

    public AddLogController(AddLogViewModel addLogViewModel) {
        this.addLogViewModel = addLogViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
