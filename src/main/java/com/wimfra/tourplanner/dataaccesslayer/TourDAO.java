package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.models.Tour;

import java.util.List;

public class TourDAO {
    private DataAccess dataAccess;

    public TourDAO(){
        dataAccess = new DBService();
    }

    public List<Tour> GetTours(){
        return dataAccess.getTours();
    }
}
