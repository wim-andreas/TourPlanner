package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.models.Tour;
import com.wimfra.tourplanner.viewmodel.TourListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TourListController implements Initializable {

    private final TourListViewModel tourListViewModel;

    // references used to setup data binding
    public ListView<Tour> tourListView;
    public TextField tourListSearch;

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

        // Bindings with TourListViewModels
        tourListSearch.textProperty().bindBidirectional(tourListViewModel.getCurrentSearchText());
    }

    public void searchAction(ActionEvent actionEvent) {
        tourItems.clear();
        tourItems.addAll(tourListViewModel.searchAction());
    }

    public void clearAction(ActionEvent actionEvent) {
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
}


