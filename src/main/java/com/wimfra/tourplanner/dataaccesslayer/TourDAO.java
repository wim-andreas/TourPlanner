package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.models.TourModel;

import java.util.List;

public class TourDAO {
    private DataAccess databaseAccess;

    public TourDAO() {
        databaseAccess = DBService.getInstance();
    }

    public List<TourModel> getTours() {
        return databaseAccess.getTours();
    }

    public TourModel getSingleTour(int id) {
        return databaseAccess.getSingleTour(id);
    }

    public int addNewTour(List<String> data) {
        return databaseAccess.addNewTour(data);
    }

    public void deleteTour(int tour_id) {
        databaseAccess.deleteTour(tour_id);
    }

    public void editTourData(List<String> data, int id) {
        databaseAccess.editTourData(data, id);
    }


}