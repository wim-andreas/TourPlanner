package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;

import java.util.List;

public class AddTourViewModel {


    // gets the connection to the business layer
    private JavaAppManager manager = JavaAppManagerFactory.GetManager();

    public void addNewTour(List<String> data){
        manager.AddNewTour(data);
    }

}
