package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.models.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TourListController implements Initializable {

    public TextField tourListSearch;
    public ListView<Tour> tourListView;

    private ObservableList<Tour> mediaItems;
    private Tour currentItem;

    private JavaAppManager manager;


    public void searchAction(ActionEvent actionEvent) {
        mediaItems.clear();
        List<Tour> items = manager.Search(tourListSearch.textProperty().getValue(), false);
        mediaItems.addAll(items);
    }

    public void clearAction(ActionEvent actionEvent) {
        mediaItems.clear();
        tourListSearch.setText("");
        mediaItems.addAll(manager.GetTours());
        tourListView.setItems(mediaItems);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manager = JavaAppManagerFactory.GetManager();

        SetupListView();
        FormatCells();
        SetCurrentItem();
    }

    private void SetupListView() {
        mediaItems = FXCollections.observableArrayList();
        mediaItems.addAll(manager.GetTours());
        tourListView.setItems(mediaItems);
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


