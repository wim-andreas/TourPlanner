package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;

public class MainWindowViewModel {
    private AddTourViewModel addTourViewModel;
    private TourListViewModel tourListViewModel;
    private EditTourViewModel editTourViewModel;
    private AddLogViewModel addLogViewModel;
    private RouteViewModel routeViewModel;
    private LogViewModel logViewModel;
    private EditLogViewModel editLogViewModel;

    private JavaAppManager appManager = JavaAppManagerFactory.GetManager();

    public MainWindowViewModel(AddTourViewModel addTourViewModel,TourListViewModel tourListViewModel, EditTourViewModel editTourViewModel, AddLogViewModel addLogViewModel, RouteViewModel routeViewModel, LogViewModel logViewModel, EditLogViewModel editLogViewModel) {
        this.addTourViewModel = addTourViewModel;
        this.tourListViewModel = tourListViewModel;
        this.editTourViewModel = editTourViewModel;
        this.addLogViewModel = addLogViewModel;
        this.routeViewModel = routeViewModel;
        this.logViewModel = logViewModel;
        this.editLogViewModel = editLogViewModel;
    }



}
