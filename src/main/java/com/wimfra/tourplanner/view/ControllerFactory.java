package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.view.AddTourController;
import com.wimfra.tourplanner.view.MainWindowController;
import com.wimfra.tourplanner.view.TourListController;
import com.wimfra.tourplanner.viewmodel.*;

public class ControllerFactory {
    private final MainWindowViewModel mainWindowViewModel;
    private final AddTourViewModel addTourViewModel;
    private final TourListViewModel tourListViewModel;
    private final RouteViewModel routeViewModel;
    private final EditTourViewModel editTourViewModel;
    private final AddLogViewModel addLogViewModel;

    public ControllerFactory() {
        this.addTourViewModel = new AddTourViewModel();
        this.tourListViewModel = new TourListViewModel();
        this.routeViewModel = new RouteViewModel();
        this.editTourViewModel = new EditTourViewModel();
        this.addLogViewModel = new AddLogViewModel();
        this.mainWindowViewModel = new MainWindowViewModel(addTourViewModel, tourListViewModel, editTourViewModel, addLogViewModel);
    }

    //
    // Factory-Method Pattern
    //

    // Callback<Class<>, Object>

    public Object create(Class<?> controllerClass){
        if(controllerClass == MainWindowController.class){
            return new MainWindowController(this.mainWindowViewModel);
        }
        else if(controllerClass == AddTourController.class){
            return new AddTourController(this.addTourViewModel);
        }
        else if(controllerClass == TourListController.class){
            return new TourListController(this.tourListViewModel);
        }
        else if(controllerClass == RouteController.class){
            return new RouteController(this.routeViewModel);
        }
        else if(controllerClass == EditTourController.class){
            return new EditTourController(this.editTourViewModel);
        }
        else if(controllerClass == AddLogController.class){
            return new AddLogController(this.addLogViewModel);
        }

        throw new IllegalArgumentException("Unknown controller class: " + controllerClass);
    }

    //
    // Singleton-Pattern with early-binding
    //

    private static ControllerFactory instance = new ControllerFactory();

    public static ControllerFactory getInstance(){ return instance; }



}
