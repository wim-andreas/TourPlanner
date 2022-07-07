package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.viewmodel.ClickMeViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ClickMeController  implements Initializable {

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
        textArea.textProperty().bindBidirectional(clickMeViewModel.textAreaProperty());
        textArea.setWrapText(true);

        clickMeBtn.setOnAction(event -> loadSecret());
    }

    private void loadSecret() {
        clickMeViewModel.loadSecret();
    }
}
