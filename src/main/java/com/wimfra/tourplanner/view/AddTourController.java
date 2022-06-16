package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.viewmodel.AddTourViewModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddTourController implements Initializable {
    @FXML
    public Button addNewTourBtn;
    @FXML
    private final AddTourViewModel addTourViewModel;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField fromTextField;
    @FXML
    public TextField toTextField;
    @FXML
    public TextField transportationTextField;
    @FXML
    public TextField durationTextField;
    @FXML
    public TextField distanceTextField;
    @FXML
    public TextField infoTextField;
    @FXML
    public TextArea descriptionTextArea;

    public AddTourController(AddTourViewModel addTourViewModel) {
        this.addTourViewModel = addTourViewModel;
    }

    // creating the bidirectional databinding
    @FXML @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        nameTextField.textProperty().bindBidirectional(addTourViewModel.nameProperty());
        fromTextField.textProperty().bindBidirectional(addTourViewModel.fromProperty());
        toTextField.textProperty().bindBidirectional(addTourViewModel.toProperty());
        transportationTextField.textProperty().bindBidirectional(addTourViewModel.transportationProperty());
        durationTextField.textProperty().bindBidirectional(addTourViewModel.durationProperty());
        distanceTextField.textProperty().bindBidirectional(addTourViewModel.distanceProperty());
        infoTextField.textProperty().bindBidirectional(addTourViewModel.infoProperty());
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
