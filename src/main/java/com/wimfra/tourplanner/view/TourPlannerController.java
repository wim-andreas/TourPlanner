package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.TourPlannerApplication;
import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.models.Tour;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TourPlannerController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manager = JavaAppManagerFactory.GetManager();

        SetupListView();
        FormatCells();
        SetCurrentItem();
    }

    private void SetupListView(){
        mediaItems = FXCollections.observableArrayList();
        mediaItems.addAll(manager.GetTours());
        tourListView.setItems(mediaItems);
    }

    private void FormatCells(){
        //format cells to show name
        tourListView.setCellFactory((param -> new ListCell<Tour>(){
            @Override
            protected void updateItem(Tour item, boolean empty){
                super.updateItem(item,empty);

                if ( empty || (item == null) || (item.getTourname() == null)){
                    setText(null);
                }
                else{
                    setText(item.getTourname());
                }
            }
        }));


    }
    private void SetCurrentItem(){
        tourListView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldvalue, newvalue) -> {
            if ((newvalue != null) && (oldvalue != newvalue)){
                currentItem = newvalue;
            }
        }));
    }
//TODO list-viw auslagern, dass es ohne fehler funktioniert
}