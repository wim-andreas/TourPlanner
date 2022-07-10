package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.viewmodel.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ControllerFactoryTest {

    private static ControllerFactory controllerFactory;
    private static AddTourViewModel addTourViewModel;
    private static TourListViewModel tourListViewModel;
    private static RouteViewModel routeViewModel;
    private static EditTourViewModel editTourViewModel;
    private static AddLogViewModel addLogViewModel;
    private static LogViewModel logViewModel;
    private static EditLogViewModel editLogViewModel;
    private static MenubarViewModel menubarViewModel;

    @BeforeAll
    public static void setUp(){
        controllerFactory = ControllerFactory.getInstance();
        addTourViewModel = new AddTourViewModel();
        tourListViewModel = new TourListViewModel();
        routeViewModel = new RouteViewModel();
        editTourViewModel = new EditTourViewModel();
        addLogViewModel = new AddLogViewModel();
        logViewModel = new LogViewModel();
        editLogViewModel = new EditLogViewModel();
        menubarViewModel = new MenubarViewModel();
    }

    @Test
    public void getAddTourControllerTest(){
        Object addTourController = controllerFactory.create(AddTourController.class);
        assertSame(addTourController.getClass().getName(),  new AddTourController(addTourViewModel).getClass().getName());
    }

    @Test
    public void getTourListController(){
        Object tourListController = controllerFactory.create(TourListController.class);
        assertSame(tourListController.getClass().getName(),  new TourListController(tourListViewModel).getClass().getName());
    }

    @Test
    public void getRouteControllerTest(){
        Object routeController = controllerFactory.create(RouteController.class);
        assertSame(routeController.getClass().getName(),  new RouteController(routeViewModel).getClass().getName());
    }

    @Test
    public void getEditTourControllerTest(){
        Object editTourController = controllerFactory.create(EditTourController.class);
        assertSame(editTourController.getClass().getName(),  new EditTourController(editTourViewModel).getClass().getName());
    }

    @Test
    public void getAddLogControllerTest(){
        Object addLogController = controllerFactory.create(AddLogController.class);
        assertSame(addLogController.getClass().getName(),  new AddLogController(addLogViewModel).getClass().getName());
    }

    @Test
    public void getLogViewControllerTest(){
        Object logViewController = controllerFactory.create(LogViewController.class);
        assertSame(logViewController.getClass().getName(),  new LogViewController(logViewModel).getClass().getName());
    }

    @Test
    public void getEditLogControllerTest(){
        Object editLogController = controllerFactory.create(EditLogController.class);
        assertSame(editLogController.getClass().getName(),  new EditLogController(editLogViewModel).getClass().getName());
    }

    @Test
    public void getMenuBarControllerTest(){
        Object menuBarController = controllerFactory.create(MenubarController.class);
        assertSame(menuBarController.getClass().getName(),  new MenubarController(menubarViewModel).getClass().getName());
    }


}
