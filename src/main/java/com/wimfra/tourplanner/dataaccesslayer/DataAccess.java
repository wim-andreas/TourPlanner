package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.models.Tour;

import java.util.List;

public interface DataAccess {
    public List<Tour> getTours();
    public Tour getSingleTour(int id);

    public Tour addNewTour(List<String> data);

    public boolean deleteTour(int tour_id);
}
