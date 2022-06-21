package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;

public class MainWindowViewModel {
    private AddTourViewModel addTourViewModel;
    private TourListViewModel tourListViewModel;
    private EditTourViewModel editTourViewModel;
    private AddLogViewModel addLogViewModel;

    private JavaAppManager appManager = JavaAppManagerFactory.GetManager();

    public MainWindowViewModel(AddTourViewModel addTourViewModel, TourListViewModel tourListViewModel, EditTourViewModel editTourViewModel, AddLogViewModel addLogViewModel) {
        this.addTourViewModel = addTourViewModel;
        this.tourListViewModel = tourListViewModel;
        this.editTourViewModel = editTourViewModel;
        this.addLogViewModel = addLogViewModel;
    }

    private void selectTour(){

    }

    public void openLogWindow() {
        appManager.openLogWindow();
    }
}
