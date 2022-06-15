package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.models.Tour;

import java.util.List;

public class TourDAO {
    private DataAccess databaseAccess;

    public TourDAO(){
        databaseAccess = new DBService();
    }

    public List<Tour> GetTours(){
        return databaseAccess.getTours();
    }

    public Tour GetSingleTour(int id){
        return databaseAccess.getSingleTour(id);
    }

    public void AddNewTour(List<String> data) {
        databaseAccess.addNewTour(data);
    }

    public void DeleteTour(int tour_id) {
        databaseAccess.deleteTour(tour_id);
    }
}
