package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.mediator.Mediator;
import com.wimfra.tourplanner.mediator.MediatorFactory;
import com.wimfra.tourplanner.viewmodel.AddTourViewModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTourController implements Initializable {

    private final AddTourViewModel addTourViewModel;
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(AddTourController.class);
    private final Mediator mediator;

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
    public TextField infoTextField;
    @FXML
    public TextArea descriptionTextArea;

    public AddTourController(AddTourViewModel addTourViewModel) {
        this.addTourViewModel = addTourViewModel;
        mediator = MediatorFactory.getMediator();
    }

    // creating the (bidirectional) databinding
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.debug("Setting up 'add-tour-view.fxml'");
        nameTextField.textProperty().bindBidirectional(addTourViewModel.nameProperty());
        fromTextField.textProperty().bindBidirectional(addTourViewModel.fromProperty());
        toTextField.textProperty().bindBidirectional(addTourViewModel.toProperty());
        transportationTextField.textProperty().bindBidirectional(addTourViewModel.transportationProperty());
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
        this.mediator.setTourID(id);
        addTourViewModel.updateTourView();
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
        infoTextField.clear();
    }

    private void createImage(int id) {
        addTourViewModel.createImage(id);
    }
}
