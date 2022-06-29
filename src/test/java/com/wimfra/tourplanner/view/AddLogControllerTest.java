package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.viewmodel.AddLogViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddLogControllerTest {
    private static final AddLogViewModel addLogViewModel = new AddLogViewModel();
    private static AddLogController addLogController;

    private TextField dateTextField = new TextField();

    @BeforeAll
    public static void setUp(){
       addLogController = new AddLogController(addLogViewModel);
    }



    @Test
    public void cleartextFieldsTest(){
        dateTextField.setText("Test");
        assertEquals("Test", dateTextField.getText());
    }
}
