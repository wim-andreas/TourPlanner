package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.view.TourListController;
import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import com.wimfra.tourplanner.viewmodel.observerpattern.ViewModel;

public class MainWindowViewModel implements ViewModel {
    private AddTourViewModel addTourViewModel;
    private TourListViewModel tourListViewModel;
    private EditTourViewModel editTourViewModel;
    private AddLogViewModel addLogViewModel;
    private RouteViewModel routeViewModel;
    private LogViewModel logViewModel;
    private EditLogViewModel editLogViewModel;
    private final Publisher observable;
    private JavaAppManager appManager = JavaAppManagerFactory.GetManager();
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(MainWindowViewModel.class);

    public MainWindowViewModel(AddTourViewModel addTourViewModel, TourListViewModel tourListViewModel, EditTourViewModel editTourViewModel, AddLogViewModel addLogViewModel, RouteViewModel routeViewModel, LogViewModel logViewModel, EditLogViewModel editLogViewModel) {
        observable = new Publisher();
        this.addTourViewModel = addTourViewModel;
        this.tourListViewModel = tourListViewModel;
        this.editTourViewModel = editTourViewModel;
        this.addLogViewModel = addLogViewModel;
        this.routeViewModel = routeViewModel;
        this.logViewModel = logViewModel;
        this.editLogViewModel = editLogViewModel;
        addObservableToViewModels();
        subscribeToObservable();
    }

    // Observer pattern methods

    private void addObservableToViewModels() {
        this.addLogViewModel.setPublisher(observable);
        this.addTourViewModel.setPublisher(observable);
        this.editLogViewModel.setPublisher(observable);
        this.editTourViewModel.setPublisher(observable);
        this.logViewModel.setPublisher(observable);
        this.routeViewModel.setPublisher(observable);
        this.tourListViewModel.setPublisher(observable);
    }

    private void subscribeToObservable() {
        observable.subscribe(this.addLogViewModel);
        observable.subscribe(this.addTourViewModel);
        observable.subscribe(this.editLogViewModel);
        observable.subscribe(this.editTourViewModel);
        observable.subscribe(this.logViewModel);
        observable.subscribe(this.routeViewModel);
        observable.subscribe(this.tourListViewModel);
    }

    public void openLogWindow() {
        //appManager.openLogWindow();
    }

    @Override
    public void updateFromDB() {
    }
}
