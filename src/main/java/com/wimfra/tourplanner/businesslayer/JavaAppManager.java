package com.wimfra.tourplanner.businesslayer;

import com.wimfra.tourplanner.models.Tour;

import java.util.List;

public interface JavaAppManager {
    List<Tour> GetTours();
    List<Tour> Search(String tourname, boolean caseSensitive);
    Tour GetSingleTour(int id);
    void AddTourWindow();
    void EditTourWindow();
    void AddNewTour(List<String> data);
    void DeleteTour(int tour_id);
}
