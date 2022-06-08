package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.viewmodel.AddTourViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTourController implements Initializable {

    private final AddTourViewModel addTourViewModel;

    public AddTourController(AddTourViewModel addTourViewModel) {
        this.addTourViewModel = addTourViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
