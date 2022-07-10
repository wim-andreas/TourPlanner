package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import com.wimfra.tourplanner.viewmodel.observerpattern.ViewModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainWindowViewModel implements ViewModel {
    private AddTourViewModel addTourViewModel;
    private TourListViewModel tourListViewModel;
    private EditTourViewModel editTourViewModel;
    private AddLogViewModel addLogViewModel;
    private RouteViewModel routeViewModel;
    private LogViewModel logViewModel;
    private EditLogViewModel editLogViewModel;
    private MenubarViewModel menubarViewModel;
    private ClickMeViewModel clickMeViewModel;
    private final Publisher observable;

    public MainWindowViewModel(AddTourViewModel addTourViewModel, TourListViewModel tourListViewModel, EditTourViewModel editTourViewModel, AddLogViewModel addLogViewModel, RouteViewModel routeViewModel, LogViewModel logViewModel, EditLogViewModel editLogViewModel, MenubarViewModel menubarViewModel, ClickMeViewModel clickMeViewModel) {
        observable = new Publisher();
        this.addTourViewModel = addTourViewModel;
        this.tourListViewModel = tourListViewModel;
        this.editTourViewModel = editTourViewModel;
        this.addLogViewModel = addLogViewModel;
        this.routeViewModel = routeViewModel;
        this.logViewModel = logViewModel;
        this.editLogViewModel = editLogViewModel;
        this.menubarViewModel = menubarViewModel;
        this.clickMeViewModel = clickMeViewModel;
        addObservableToViewModels();
        subscribeToObservable();
    }

    // Observer pattern methods
    private void addObservableToViewModels() {
        this.addLogViewModel.setPublisher(observable);
        this.addTourViewModel.setPublisher(observable);
        this.clickMeViewModel.setPublisher(observable);
        this.editLogViewModel.setPublisher(observable);
        this.editTourViewModel.setPublisher(observable);
        this.logViewModel.setPublisher(observable);
        this.menubarViewModel.setPublisher(observable);
        this.routeViewModel.setPublisher(observable);
        this.tourListViewModel.setPublisher(observable);
    }

    private void subscribeToObservable() {
        observable.subscribe(this.addLogViewModel);
        observable.subscribe(this.addTourViewModel);
        observable.subscribe(this.clickMeViewModel);
        observable.subscribe(this.editLogViewModel);
        observable.subscribe(this.editTourViewModel);
        observable.subscribe(this.logViewModel);
        observable.subscribe(this.menubarViewModel);
        observable.subscribe(this.routeViewModel);
        observable.subscribe(this.tourListViewModel);
    }

    @Override
    public void updateFromDB() {
    }

    public Publisher getPublisher() {
        return observable;
    }
}
