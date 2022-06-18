package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.models.Tour;

public class MainWindowViewModel {

    private AddTourViewModel addTourViewModel;
    private TourListViewModel tourListViewModel;
    private EditTourViewModel editTourViewModel;

    public MainWindowViewModel(AddTourViewModel addTourViewModel, TourListViewModel tourListViewModel, EditTourViewModel editTourViewModel) {
        this.addTourViewModel = addTourViewModel;
        this.tourListViewModel = tourListViewModel;
        this.editTourViewModel = editTourViewModel;

    }

    private void selectTour(){

    }
}
