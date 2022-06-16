package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.view.AddTourController;
import com.wimfra.tourplanner.view.MainWindowController;
import com.wimfra.tourplanner.view.TourListController;
import com.wimfra.tourplanner.viewmodel.AddTourViewModel;
import com.wimfra.tourplanner.viewmodel.MainWindowViewModel;
import com.wimfra.tourplanner.viewmodel.RouteViewModel;
import com.wimfra.tourplanner.viewmodel.TourListViewModel;

public class ControllerFactory {
    private final MainWindowViewModel mainWindowViewModel;
    private final AddTourViewModel addTourViewModel;
    private final TourListViewModel tourListViewModel;
    private final RouteViewModel routeViewModel;

    public ControllerFactory() {
        this.addTourViewModel = new AddTourViewModel();
        this.tourListViewModel = new TourListViewModel();
        this.routeViewModel = new RouteViewModel();
        this.mainWindowViewModel = new MainWindowViewModel(addTourViewModel, tourListViewModel);
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

        throw new IllegalArgumentException("Unknown controller class: " + controllerClass);
    }

    //
    // Singleton-Pattern with early-binding
    //

    private static ControllerFactory instance = new ControllerFactory();

    public static ControllerFactory getInstance(){ return instance; }



}
