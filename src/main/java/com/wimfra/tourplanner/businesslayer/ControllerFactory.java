package com.wimfra.tourplanner.businesslayer;

import com.wimfra.tourplanner.view.AddTourController;
import com.wimfra.tourplanner.view.MainWindowController;
import com.wimfra.tourplanner.view.TourListController;
import com.wimfra.tourplanner.viewmodel.AddTourViewModel;
import com.wimfra.tourplanner.viewmodel.MainWindowViewModel;
import com.wimfra.tourplanner.viewmodel.TourListViewModel;

public class ControllerFactory {
    private final MainWindowViewModel mainWindowViewModel;
    private final AddTourViewModel addTourViewModel;
    private final TourListViewModel tourListViewModel;

    public ControllerFactory() {
        this.addTourViewModel = new AddTourViewModel();
        this.tourListViewModel = new TourListViewModel();
        this.mainWindowViewModel = new MainWindowViewModel(addTourViewModel, tourListViewModel);
    }

    public Object create(Class<?> controllerClass){
        if(controllerClass == MainWindowViewModel.class){
            return new MainWindowController(this.mainWindowViewModel);
        }
        else if(controllerClass == AddTourViewModel.class){
            return new AddTourController(this.addTourViewModel);
        }
        else if(controllerClass == TourListViewModel.class){
            return new TourListController(this.tourListViewModel);
        }

        throw new IllegalArgumentException("Unknown controller class: " + controllerClass);
    }


}
