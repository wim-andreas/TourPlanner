package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;

public class AddTourViewModel {

    // gets the connection to the business layer
    private JavaAppManager manager = JavaAppManagerFactory.GetManager();

}
