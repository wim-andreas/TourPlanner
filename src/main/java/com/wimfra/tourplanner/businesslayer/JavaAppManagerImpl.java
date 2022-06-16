package com.wimfra.tourplanner.businesslayer;



import com.wimfra.tourplanner.TourPlannerApplication;
import com.wimfra.tourplanner.dataaccesslayer.TourDAO;
import com.wimfra.tourplanner.models.Tour;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JavaAppManagerImpl implements JavaAppManager{
TourDAO tourDAO = new TourDAO();

    @Override
    public List<Tour> GetTours() {
        return tourDAO.GetTours();
    }

    @Override
    public List<Tour> Search(String tourname, boolean caseSensitive) {
        List<Tour>items = GetTours();

        if(caseSensitive){
            return items
                    .stream()
                    .filter(x -> x.getTour_name().contains(tourname))
                    .collect(Collectors.toList());
        }
        return items
                .stream()
                .filter(x -> x.getTour_name().toLowerCase().contains(tourname.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Tour GetSingleTour(int id) {
        return tourDAO.GetSingleTour(id);
    }

    @Override
    public void AddTourWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(TourPlannerApplication.class.getResource("add-tour-view.fxml"));
        Scene scene = null;
        Stage stage = new Stage();
        try {
            scene = new Scene(fxmlLoader.load(), 820, 640);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Add a new tour");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void EditTourWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(TourPlannerApplication.class.getResource("edit-tour-view.fxml"));
        Scene scene = null;
        Stage stage = new Stage();
        try {
            scene = new Scene(fxmlLoader.load(), 820, 640);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Edit a tour");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void AddNewTour(List<String> data) {
        tourDAO.AddNewTour(data);
    }
    @Override
    public void DeleteTour(int tour_id) {
        tourDAO.DeleteTour(tour_id);
    }

    @Override
    public void EditTourData(List<String> data, int id) {
        tourDAO.EditTourData(data, id);
    }
}
