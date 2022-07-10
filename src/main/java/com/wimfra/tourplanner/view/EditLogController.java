package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.mediator.Mediator;
import com.wimfra.tourplanner.mediator.MediatorFactory;
import com.wimfra.tourplanner.viewmodel.EditLogViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditLogController implements Initializable {
    @Getter
    @Setter
    private final EditLogViewModel editLogViewModel;
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(EditLogController.class);
    private Mediator mediator;

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
    public Button editLogBtn;
    @FXML
    public Button closeWindowBtn;

    public EditLogController(EditLogViewModel editLogViewModel) {
        this.editLogViewModel = editLogViewModel;
        this.mediator = MediatorFactory.getMediator();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.debug("Setting up 'edit-log-view.fxml'");
        ratingSpinner.promptTextProperty().bindBidirectional(editLogViewModel.ratingProperty());
        dateTextField.textProperty().bindBidirectional(editLogViewModel.dateProperty());
        timeTextField.textProperty().bindBidirectional(editLogViewModel.timeProperty());
        totalTimeTextField.textProperty().bindBidirectional(editLogViewModel.totalTimeProperty());
        commentTextArea.textProperty().bindBidirectional(editLogViewModel.commentProperty());
        commentTextArea.setWrapText(true);

        editLogBtn.setOnAction(event -> editLogData());
        closeWindowBtn.setOnAction(event -> closeCurrentWindow());

        setUpChoiceBox();
        setUpSpinner();
        List<String> log = editLogViewModel.getSingleLog(mediator.getLogID());
        if (null != log) {
            loadLogData(log);
        }

    }

    public void setUpChoiceBox() {
        difficultyChoiceBox.getItems().add("beginner");
        difficultyChoiceBox.getItems().add("average");
        difficultyChoiceBox.getItems().add("expert");
        difficultyChoiceBox.getItems().add("GOAT");
        difficultyChoiceBox.setValue("Choose a difficulty...");
    }

    private void setUpSpinner() {
        SpinnerValueFactory.IntegerSpinnerValueFactory integerSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        ratingSpinner.setValueFactory(integerSpinnerValueFactory);
        //ratingSpinner.getValueFactory().setValue(4);
    }

    private void loadLogData(List<String> log) {
        logger.debug("Loading selected log data...");
        dateTextField.setText(log.get(0));
        timeTextField.setText(log.get(1));
        difficultyChoiceBox.setValue(log.get(2));
        ratingSpinner.getValueFactory().setValue(Integer.valueOf(log.get(3)));
        commentTextArea.setText(log.get(4));
        totalTimeTextField.setText(log.get(5));
    }

    private void editLogData() {
        logger.debug("Editing the selected log...");
        //Get Selected Tour ID date_, time_, comment_, difficulty, total_time, rating
        List<String> data = new ArrayList();
        data.add(0, dateTextField.getText());
        data.add(1, timeTextField.getText());
        data.add(2, difficultyChoiceBox.getSelectionModel().getSelectedItem().toString());
        ;
        data.add(3, ratingSpinner.getValue().toString());
        data.add(4, commentTextArea.getText());
        data.add(5, totalTimeTextField.getText());

        editLogViewModel.editLogData(data, mediator.getLogID());
        closeCurrentWindow();
    }

    public void clearTextFields() {
        dateTextField.clear();
        timeTextField.clear();
        totalTimeTextField.clear();
        commentTextArea.clear();

    }

    private void closeCurrentWindow() {
        clearTextFields();
        Stage stage = (Stage) editLogBtn.getScene().getWindow();
        stage.close();
    }
}
