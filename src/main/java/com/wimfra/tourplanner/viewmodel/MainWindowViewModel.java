package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import com.wimfra.tourplanner.viewmodel.observerpattern.ViewModel;

public class MainWindowViewModel implements ViewModel {
    private AddTourViewModel addTourViewModel;
    private TourListViewModel tourListViewModel;
    private EditTourViewModel editTourViewModel;
    private AddLogViewModel addLogViewModel;
    private RouteViewModel routeViewModel;
    private final Publisher observable;

    private JavaAppManager appManager = JavaAppManagerFactory.GetManager();

    public MainWindowViewModel(AddTourViewModel addTourViewModel, TourListViewModel tourListViewModel, EditTourViewModel editTourViewModel, AddLogViewModel addLogViewModel, RouteViewModel routeViewModel) {
        observable = new Publisher();
        this.addTourViewModel = addTourViewModel;
        this.tourListViewModel = tourListViewModel;
        this.editTourViewModel = editTourViewModel;
        this.addLogViewModel = addLogViewModel;
        this.routeViewModel = routeViewModel;
        addObservableToViewModels();
        subscribeToObservable();
    }

    // Observer pattern methods

    private void addObservableToViewModels(){
        this.addTourViewModel.setPublisher(observable);
        this.tourListViewModel.setPublisher(observable);
        this.editTourViewModel.setPublisher(observable);
        this.addLogViewModel.setPublisher(observable);
        this.routeViewModel.setPublisher(observable);
    }

    private void subscribeToObservable(){
        observable.subscribe(this.addTourViewModel);
        observable.subscribe(this.tourListViewModel);
        observable.subscribe(this.editTourViewModel);
        observable.subscribe(this.addLogViewModel);
        observable.subscribe(this.routeViewModel);
    }

    public void openLogWindow() {
        appManager.openLogWindow();
    }

    @Override
    public void updateFromDB() {

    }
}
