package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.mediator.Mediator;
import com.wimfra.tourplanner.mediator.MediatorFactory;
import com.wimfra.tourplanner.models.TourModel;
import com.wimfra.tourplanner.viewmodel.EditTourViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditTourController implements Initializable {
    @Getter
    @Setter
    private final EditTourViewModel editTourViewModel;
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(EditTourController.class);
    private final Mediator mediator;

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
    @FXML
    public Button editTourBtn;
    @FXML
    public Button closeTourBtn;

    public EditTourController(EditTourViewModel editTourViewModel) {
        this.editTourViewModel = editTourViewModel;
        this.mediator = MediatorFactory.getMediator();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.debug("Setting up 'edit-tour-view.fxml'");
        nameTextField.textProperty().bindBidirectional(editTourViewModel.nameProperty());
        fromTextField.textProperty().bindBidirectional(editTourViewModel.fromProperty());
        toTextField.textProperty().bindBidirectional(editTourViewModel.toProperty());
        transportationTextField.textProperty().bindBidirectional(editTourViewModel.transportationProperty());
        infoTextField.textProperty().bindBidirectional(editTourViewModel.infoProperty());
        descriptionTextArea.textProperty().bindBidirectional(editTourViewModel.descriptionProperty());
        descriptionTextArea.setWrapText(true);

        editTourBtn.setOnAction(event -> editTourData());
        closeTourBtn.setOnAction(event -> closeCurrentWindow());

        TourModel tour = editTourViewModel.getSingleTour(mediator.getTourID());
        if (null != tour) {
            loadTourData(tour);
        }
    }

    public void editTourData() {
        logger.debug("Editing the selected tour...");
        //Get Selected Tour ID
        List<String> data = new ArrayList();
        data.add(0, nameTextField.getText());
        data.add(1, descriptionTextArea.getText());
        data.add(2, fromTextField.getText());
        data.add(3, toTextField.getText());
        data.add(4, transportationTextField.getText());
        data.add(5, infoTextField.getText());

        editTourViewModel.editTourData(data, mediator.getTourID());
        closeCurrentWindow();
    }

    public void loadTourData(TourModel tour) {
        logger.debug("Loading selected tour data...");
        nameTextField.setText(tour.getTour_name());
        fromTextField.setText(tour.getFrom_where());
        toTextField.setText(tour.getTo_where());
        transportationTextField.setText(tour.getTransportation());
        infoTextField.setText(tour.getRoute_info());
        descriptionTextArea.setText(tour.getDescription());
    }

    public void clearTextFields() {
        nameTextField.clear();
        descriptionTextArea.clear();
        fromTextField.clear();
        toTextField.clear();
        transportationTextField.clear();
        infoTextField.clear();
    }

    private void closeCurrentWindow() {
        clearTextFields();
        Stage stage = (Stage) editTourBtn.getScene().getWindow();
        stage.close();
    }
}
