package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;
import com.wimfra.tourplanner.models.Tour;

import java.util.List;

public class EditTourViewModel {
    // gets the connection to the business layer
    private JavaAppManager manager = JavaAppManagerFactory.GetManager();
    private ManageTourService tourService = new ManageTourServiceImpl();

    public Tour getSingleTour(int id) {
        return tourService.getSingleTour(id);
    }

    public void editTourData(List<String> data, int id) {
        tourService.editTourData(data, id);
    }
}
