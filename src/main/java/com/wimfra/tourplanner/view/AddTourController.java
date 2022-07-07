package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.models.TourModel;
import com.wimfra.tourplanner.viewmodel.AddLogViewModel;
import com.wimfra.tourplanner.viewmodel.AddTourViewModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddTourController implements Initializable {

    private final AddTourViewModel addTourViewModel;
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(AddTourController.class);

    @FXML
    public Button addNewTourBtn;
    @FXML
    public Button closeWindowBtn;
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

    // creating the (bidirectional) databinding
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameTextField.textProperty().bindBidirectional(addTourViewModel.nameProperty());
        fromTextField.textProperty().bindBidirectional(addTourViewModel.fromProperty());
        toTextField.textProperty().bindBidirectional(addTourViewModel.toProperty());
        transportationTextField.textProperty().bindBidirectional(addTourViewModel.transportationProperty());
        durationTextField.textProperty().bindBidirectional(addTourViewModel.durationProperty());
        distanceTextField.textProperty().bindBidirectional(addTourViewModel.distanceProperty());
        infoTextField.textProperty().bindBidirectional(addTourViewModel.infoProperty());
        descriptionTextArea.textProperty().bindBidirectional(addTourViewModel.descriptionProperty());
        descriptionTextArea.setWrapText(true);
        addNewTourBtn.setOnAction(event -> addNewTour());
        closeWindowBtn.setOnAction(event -> closeCurrentWindow());
    }

    public void addNewTour() {
        int id = addTourViewModel.addNewTour();
        createImage(id);
        closeCurrentWindow();
    }

    private void closeCurrentWindow() {
        clearTextFields();
        Stage stage = (Stage) addNewTourBtn.getScene().getWindow();
        stage.close();
    }

    public void clearTextFields() {
        nameTextField.clear();
        descriptionTextArea.clear();
        fromTextField.clear();
        toTextField.clear();
        transportationTextField.clear();
        distanceTextField.clear();
        durationTextField.clear();
        infoTextField.clear();
    }

    private void createImage(int id) {
        addTourViewModel.createImage(id);
    }
}
