package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.models.Tour;
import com.wimfra.tourplanner.viewmodel.EditTourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditTourController implements Initializable {

    private EditTourViewModel editTourViewModel = new EditTourViewModel();

    public TextField nameTextField;
    public TextField fromTextField;
    public TextField toTextField;
    public TextField transportationTextField;
    public TextField durationTextField;
    public TextField distanceTextField;
    public TextField infoTextField;
    public TextArea descriptionTextArea;

    public Button editTourBtn;


//Todo über die Controller factory eine verbindung aufbauen, sodass eine selected Tour bearbeitet werden kann

    public void editTourData(ActionEvent actionEvent){
        //Get Selected Tour ID
        int id = 1;
        List<String> data = new ArrayList();
        data.add(0,nameTextField.getText());
        data.add(1,descriptionTextArea.getText());
        data.add(2,fromTextField.getText());
        data.add(3,toTextField.getText());
        data.add(4,transportationTextField.getText());
        data.add(5,distanceTextField.getText());
        data.add(6,durationTextField.getText());
        data.add(7,infoTextField.getText());


        editTourViewModel.editTourData(data, id);
        Stage stage = (Stage) editTourBtn.getScene().getWindow();
        stage.close();
    }

    public void loadTourData(Tour tour){
        nameTextField.setText(tour.getTour_name());
        fromTextField.setText(tour.getFrom_where());
        toTextField.setText(tour.getTo_where());
        transportationTextField.setText(tour.getTransportation());
        durationTextField.setText(tour.getDuration());
        distanceTextField.setText(tour.getDistance());
        infoTextField.setText(tour.getRoute_info());
        descriptionTextArea.setText(tour.getDescription());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tour tour = editTourViewModel.getSingleTour(1);
        loadTourData(tour);
    }
}
