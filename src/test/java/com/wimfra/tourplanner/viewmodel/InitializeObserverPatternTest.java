package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.view.*;
import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitializeObserverPatternTest {
    ControllerFactory controllerFactory = ControllerFactory.getInstance();
    MainWindowController mainWindowController = (MainWindowController) controllerFactory.create(MainWindowController.class);
    Publisher observer;

    @BeforeEach
    void setUp(){
        // Getting the "Publisher (Observer)" from the MainWindowViewModel
        observer = mainWindowController.getMainWindowViewModel().getPublisher();
    }


    @Test
    public void NumberOfSubscribersFitExpectedTest(){
        //check how many ViewModels Subscribed to the publisher
        assertSame(observer.getSubscribers().size(), 9);
    }

    @Test
    public void AddLogViewModelIsSubbedTest(){
        AddLogController addLogController = (AddLogController) controllerFactory.create(AddLogController.class);
        //check if the observer and the viewmodel are the same whenever they are being created from the controllerfactory
        assertSame(mainWindowController.getMainWindowViewModel().getAddLogViewModel(), addLogController.getAddLogViewModel());
        assertSame(observer, addLogController.getAddLogViewModel().getPublisher());
    }

    @Test
    public void AddTourViewModelIsSubbedTest(){
        AddTourController addTourController = (AddTourController) controllerFactory.create(AddTourController.class);
        //check if the observer and the viewmodel are the same whenever they are being created from the controllerfactory
        assertSame(mainWindowController.getMainWindowViewModel().getAddTourViewModel(), addTourController.getAddTourViewModel());
        assertSame(observer, addTourController.getAddTourViewModel().getPublisher());
    }

    @Test
    public void ClickMeViewModelIsSubbedTest(){
        ClickMeController clickMeController = (ClickMeController) controllerFactory.create(ClickMeController.class);
        //check if the observer and the viewmodel are the same whenever they are being created from the controllerfactory
        assertSame(mainWindowController.getMainWindowViewModel().getClickMeViewModel(), clickMeController.getClickMeViewModel());
        assertSame(observer, clickMeController.getClickMeViewModel().getPublisher());
    }

    @Test
    public void EditLogViewModelIsSubbedTest(){
        EditLogController editLogController = (EditLogController) controllerFactory.create(EditLogController.class);
        //check if the observer and the viewmodel are the same whenever they are being created from the controllerfactory
        assertSame(mainWindowController.getMainWindowViewModel().getEditLogViewModel(), editLogController.getEditLogViewModel());
        assertSame(observer, editLogController.getEditLogViewModel().getPublisher());
    }

    @Test
    public void EditTourViewModelIsSubbedTest(){
        EditTourController editTourController = (EditTourController) controllerFactory.create(EditTourController.class);
        //check if the observer and the viewmodel are the same whenever they are being created from the controllerfactory
        assertSame(mainWindowController.getMainWindowViewModel().getEditTourViewModel(), editTourController.getEditTourViewModel());
        assertSame(observer, editTourController.getEditTourViewModel().getPublisher());
    }

    @Test
    public void LogViewModelIsSubbedTest(){
        LogViewController logViewController = (LogViewController) controllerFactory.create(LogViewController.class);
        //check if the observer and the viewmodel are the same whenever they are being created from the controllerfactory
        assertSame(mainWindowController.getMainWindowViewModel().getLogViewModel(), logViewController.getLogViewModel());
        assertSame(observer, logViewController.getLogViewModel().getPublisher());
    }

    @Test
    public void MenubarViewModelIsSubbedTest(){
        MenubarController menubarController = (MenubarController) controllerFactory.create(MenubarController.class);
        //check if the observer and the viewmodel are the same whenever they are being created from the controllerfactory
        assertSame(mainWindowController.getMainWindowViewModel().getMenubarViewModel(), menubarController.getMenubarViewModel());
        assertSame(observer, menubarController.getMenubarViewModel().getPublisher());
    }

    @Test
    public void RouteViewModelIsSubbedTest(){
        RouteController routeController = (RouteController) controllerFactory.create(RouteController.class);
        //check if the observer and the viewmodel are the same whenever they are being created from the controllerfactory
        assertSame(mainWindowController.getMainWindowViewModel().getRouteViewModel(), routeController.getRouteViewModel());
        assertSame(observer, routeController.getRouteViewModel().getPublisher());
    }

    @Test
    public void TourListViewModelIsSubbedTest(){
        TourListController tourListController = (TourListController) controllerFactory.create(TourListController.class);
        //check if the observer and the viewmodel are the same whenever they are being created from the controllerfactory
        assertSame(mainWindowController.getMainWindowViewModel().getTourListViewModel(), tourListController.getTourListViewModel());
        assertSame(observer, tourListController.getTourListViewModel().getPublisher());
    }

}