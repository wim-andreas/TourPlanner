package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.viewmodel.ClickMeViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ClickMeController implements Initializable {

    private static final ILoggerWrapper logger = LoggerFactory.getLogger(ClickMeController.class);

    private final ClickMeViewModel clickMeViewModel;
    @FXML
    public Button clickMeBtn;
    @FXML
    public TextArea textArea;

    public ClickMeController(ClickMeViewModel clickMeViewModel) {
        this.clickMeViewModel = clickMeViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.debug("Setting up 'add-log-view.fxml'");
        textArea.textProperty().bindBidirectional(clickMeViewModel.textAreaProperty());
        textArea.setWrapText(true);

        clickMeBtn.setOnAction(event -> loadSecret());
    }

    private void loadSecret() {
        clickMeViewModel.loadSecret();
    }
}
