package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.models.Tour;

import java.util.List;

public class EditTourViewModel {

    // gets the connection to the business layer
    private JavaAppManager manager = JavaAppManagerFactory.GetManager();

    public Tour getSingleTour(int id) {
        return manager.GetSingleTour(id);
    }

    public void editTourData(List<String> data, int id) {
        manager.EditTourData(data, id);
    }
}
