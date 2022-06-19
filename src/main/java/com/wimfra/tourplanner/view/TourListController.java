package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.TourPlannerApplication;
import com.wimfra.tourplanner.models.Tour;
import com.wimfra.tourplanner.viewmodel.TourListViewModel;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TourListController implements Initializable {

    private final TourListViewModel tourListViewModel;

    // references used to setup data binding
    @FXML
    public ListView<Tour> tourListView;
    @FXML
    public TextField tourListSearch;
    @FXML
    public Button addTourBtn;
    @FXML
    public Button editTourBtn;
    @FXML
    public Button deleteTourBtn;
    @FXML
    public Button searchButton;
    @FXML
    public Button clearButton;

    public int tour_id;

    private ObservableList<Tour> tourItems;
    private Tour currentItem;

    public TourListController(TourListViewModel tourListViewModel) {
        this.tourListViewModel = tourListViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SetupListView();
        FormatCells();
        SetCurrentItem();

        // Bindings with TourListViewModel
        tourListSearch.textProperty().bindBidirectional(tourListViewModel.getCurrentSearchText());

        // Setting events that happen on button press
        addTourBtn.setOnAction(event->addNewTourWindow());
        editTourBtn.setOnAction(event->editTourWindow());
        deleteTourBtn.setOnAction(event->deleteTour());
        searchButton.setOnAction(event->searchAction());
        clearButton.setOnAction(event->clearAction());
        tourListView.setOnMouseClicked(event->onMouseClickGetTour());
    }

    public void searchAction() {
        tourItems.clear();
        tourItems.addAll(tourListViewModel.searchAction());
    }

    public void clearAction() {
        tourItems.clear();
        tourListSearch.setText("");
        tourItems.addAll(tourListViewModel.getTourItems());
        tourListView.setItems(tourItems);
    }

    private void SetupListView() {
        tourItems = FXCollections.observableArrayList();
        tourItems.addAll(tourListViewModel.getTourItems());
        tourListView.setItems(tourItems);
    }

    private void FormatCells() {
        //format cells to show name
        tourListView.setCellFactory((param -> new ListCell<Tour>() {
            @Override
            protected void updateItem(Tour item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || (item == null) || (item.getTour_name() == null)) {
                    setText(null);
                } else {
                    setText(item.getTour_name());
                }
            }
        }));
    }

    private void SetCurrentItem() {
        tourListView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldvalue, newvalue) -> {
            if ((newvalue != null) && (oldvalue != newvalue)) {
                currentItem = newvalue;
            }
        }));
    }

    public void onMouseClickGetTour(/*javafx.scene.input.MouseEvent mouseEvent*/) {

    if(tourListView.getSelectionModel().getSelectedItem() == null){

      }
    else{
       tour_id = tourListView.getSelectionModel().getSelectedItem().getTour_id();
       tourListViewModel.getSingleTour(tour_id);
    }

    }

    public void addNewTourWindow()  {
        tourListViewModel.addNewTourWindow();
        SetupListView();
        FormatCells();
        SetCurrentItem();
    }

    public void editTourWindow() {
        tourListViewModel.editTourWindow();
        SetupListView();
        FormatCells();
        SetCurrentItem();
    }

    public void deleteTour() {
        tourListViewModel.deleteTour(tour_id);
        SetupListView();
        FormatCells();
        SetCurrentItem();
    }
}



