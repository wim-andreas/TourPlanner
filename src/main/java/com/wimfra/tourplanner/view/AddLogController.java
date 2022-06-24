package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.mediator.Mediator;
import com.wimfra.tourplanner.mediator.MediatorFactory;
import com.wimfra.tourplanner.viewmodel.AddLogViewModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;


import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AddLogController implements Initializable {

    private final AddLogViewModel addLogViewModel;
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(AddLogController.class);

    @FXML
    public ChoiceBox difficultyChoiceBox;
    @FXML
    public TextField dateTextField;
    @FXML
    public TextField timeTextField;
    @FXML
    public TextField totalTimeTextField;
    @FXML
    public TextArea commentTextArea;
    @FXML
    public Spinner ratingSpinner;
    @FXML
    public Button addNewLogBtn;
    @FXML
    public Button closeWindowBtn;

    private Mediator mediator;

    public AddLogController(AddLogViewModel addLogViewModel) {
        this.addLogViewModel = addLogViewModel;
        this.mediator = MediatorFactory.getMediator();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.debug("Setting up 'add-log-view.fxml'");
        // difficultyChoiceBox
        ratingSpinner.promptTextProperty().bindBidirectional(addLogViewModel.ratingProperty());
        dateTextField.textProperty().bindBidirectional(addLogViewModel.dateProperty());
        timeTextField.textProperty().bindBidirectional(addLogViewModel.timeProperty());
        totalTimeTextField.textProperty().bindBidirectional(addLogViewModel.totalTimeProperty());
        commentTextArea.textProperty().bindBidirectional(addLogViewModel.commentProperty());
        commentTextArea.setWrapText(true);


        setUpChoiceBox();
        setUpSpinner();
        setUpDate();
        setUpTime();
        addNewLogBtn.setOnAction(event -> addNewLog());
        closeWindowBtn.setOnAction(event -> closeCurrentWindow());
    }

    public void addNewLog() {
        logger.debug("Creating new log in database...");
        String dif = difficultyChoiceBox.getSelectionModel().getSelectedItem().toString();
        String rat = ratingSpinner.getValue().toString();
        addLogViewModel.addNewLog(dif, rat, mediator.getTourID());
        closeCurrentWindow();
    }

    private void setUpDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatted_date = date.format(formatter);
        dateTextField.setText(formatted_date);
    }

    private void setUpTime() {
        LocalTime time = LocalTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String formattedTime = time.format(formatter);

        timeTextField.setText(formattedTime);
    }

    public void setUpChoiceBox() {
        difficultyChoiceBox.getItems().add("beginner");
        difficultyChoiceBox.getItems().add("average");
        difficultyChoiceBox.getItems().add("expert");
        difficultyChoiceBox.getItems().add("GOAT");
        difficultyChoiceBox.setValue("Choose a difficulty...");

    }

    private void closeCurrentWindow() {
        clearTextFields();
        Stage stage = (Stage) addNewLogBtn.getScene().getWindow();
        stage.close();
    }

    private void clearTextFields() {
        dateTextField.clear();
        timeTextField.clear();
        totalTimeTextField.clear();
        commentTextArea.clear();
    }

    private void setUpSpinner() {
        SpinnerValueFactory.IntegerSpinnerValueFactory integerSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        ratingSpinner.setValueFactory(integerSpinnerValueFactory);
    }
}
