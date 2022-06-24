package com.wimfra.tourplanner.businesslayer;

import com.wimfra.tourplanner.models.Tour;

import java.util.List;

public interface ManageTourService {
    List<Tour> getTours();

    List<Tour> search(String tourname, boolean caseSensitive);

    Tour getSingleTour(int id);

    void addNewTour(List<String> data);

    void deleteTour(int tour_id);

    void editTourData(List<String> data, int id);
}
