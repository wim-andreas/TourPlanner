package com.wimfra.tourplanner.businesslayer;

import com.wimfra.tourplanner.models.TourModel;

import java.util.List;

public interface ManageTourService {
    List<TourModel> getTours();

    List<TourModel> search(String tourname, boolean caseSensitive);

    TourModel getSingleTour(int id);

    void addNewTour(List<String> data);

    void deleteTour(int tour_id);

    void editTourData(List<String> data, int id);
}
