package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.businesslayer.ManageTourLogServiceImpl;

public class AddLogViewModel {

    private JavaAppManager appManager = JavaAppManagerFactory.GetManager();

    private ManageTourLogServiceImpl manageTourLogService = new ManageTourLogServiceImpl();



}
