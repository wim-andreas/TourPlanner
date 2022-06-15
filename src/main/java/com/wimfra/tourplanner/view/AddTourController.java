package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.viewmodel.AddTourViewModel;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AddTourController {

    public Button addNewTourBtn;
    private AddTourViewModel addTourViewModel = new AddTourViewModel();

    public TextField nameTextField;
    public TextField fromTextField;
    public TextField toTextField;
    public TextField transportationTextField;
    public TextField durationTextField;
    public TextField distanceTextField;
    public TextField infoTextField;
    public TextArea descriptionTextArea;

    public AddTourController(AddTourViewModel addTourViewModel) {
    }

    public void addNewTour(ActionEvent actionEvent) {

        List<String> data = new ArrayList();
        data.add(0,nameTextField.getText());
        data.add(1,descriptionTextArea.getText());
        data.add(2,fromTextField.getText());
        data.add(3,toTextField.getText());
        data.add(4,transportationTextField.getText());
        data.add(5,distanceTextField.getText());
        data.add(6,durationTextField.getText());
        data.add(7,infoTextField.getText());

        nameTextField.clear();
        descriptionTextArea.clear();
        fromTextField.clear();
        toTextField.clear();
        transportationTextField.clear();
        distanceTextField.clear();
        durationTextField.clear();
        infoTextField.clear();

        addTourViewModel.addNewTour(data);

        Stage stage = (Stage) addNewTourBtn.getScene().getWindow();
        stage.close();



    }
}
