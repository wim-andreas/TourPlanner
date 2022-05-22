package com.wimfra.tourplanner.businesslayer;

import com.wimfra.tourplanner.models.Tour;

import java.util.List;

public interface JavaAppManager {
    List<Tour> GetTours();
    List<Tour> Search(String tourname, boolean caseSensitive);
}
