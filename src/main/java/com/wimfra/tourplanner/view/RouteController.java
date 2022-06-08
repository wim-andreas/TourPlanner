package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.models.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class RouteController implements Initializable {

    private JavaAppManager manager;

    public Tab description_tab;
    public Text description;
    public ListView<Tour> tourListView;

    private ObservableList<Tour> mediaItems;
    private Tour currentItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manager = JavaAppManagerFactory.GetManager();
        description.setText("Dummy daten: dsfds\n dsgsdg");

    }

    private void SetupListView() {

    }
}
