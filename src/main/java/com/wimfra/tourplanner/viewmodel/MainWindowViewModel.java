package com.wimfra.tourplanner.viewmodel;

public class MainWindowViewModel {

    private AddTourViewModel addTourViewModel;
    private TourListViewModel tourListViewModel;

    public MainWindowViewModel(AddTourViewModel addTourViewModel, TourListViewModel tourListViewModel) {
        this.addTourViewModel = addTourViewModel;
        this.tourListViewModel = tourListViewModel;
    }
}
