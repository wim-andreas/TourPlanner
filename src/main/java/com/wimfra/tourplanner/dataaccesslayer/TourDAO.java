package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.models.TourModel;

import java.util.List;
import java.util.Optional;

public class TourDAO {
    private DataAccess databaseAccess;

    public TourDAO() {
        databaseAccess = DBService.getInstance();
    }

    public List<TourModel> GetTours() {
        return databaseAccess.getTours();
    }

    public TourModel GetSingleTour(int id) {
        return databaseAccess.getSingleTour(id);
    }

    public void AddNewTour(List<String> data) {
        databaseAccess.addNewTour(data);
    }

    public void DeleteTour(int tour_id) {
        databaseAccess.deleteTour(tour_id);
    }

    public void EditTourData(List<String> data, int id) {
        databaseAccess.editTourData(data, id);
    }
}