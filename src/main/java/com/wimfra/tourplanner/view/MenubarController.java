package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.mediator.Mediator;
import com.wimfra.tourplanner.mediator.MediatorFactory;
import com.wimfra.tourplanner.viewmodel.MenubarViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import lombok.extern.log4j.Log4j2;

import java.net.URL;
import java.util.ResourceBundle;

public class MenubarController implements Initializable {
    private final MenubarViewModel menubarViewModel;
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(MenubarController.class);
    private final Mediator mediator;

    @FXML
    public MenuItem import_item;
    @FXML
    public MenuItem export_item;
    @FXML
    public MenuItem summarize_report_item;
    @FXML
    public MenuItem tour_report_item;
    @FXML
    public MenuItem delete_item;
    @FXML
    public MenuItem about_item;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        import_item.setOnAction(actionEvent -> importItem());
        export_item.setOnAction(actionEvent -> exportItem());
        summarize_report_item.setOnAction(actionEvent -> generateSummarizeReport());
        tour_report_item.setOnAction(actionEvent -> generateTourReport());
        delete_item.setOnAction(actionEvent -> deleteItem());
        about_item.setOnAction(actionEvent -> aboutItem());
    }

    //TODO: write those functions to create functionality of the toolbar!
    private void importItem() {
        menubarViewModel.importTourItem();
    }

    private void exportItem() {
        menubarViewModel.exportTourItem(mediator.getTourID());
    }

    private void generateSummarizeReport() {
        logger.debug("Generating a summarized report of all tours...");
        menubarViewModel.generateSummarizeReport();
    }

    private void generateTourReport() {
        menubarViewModel.generateTourReport(mediator.getTourID());
    }

    private void deleteItem() {
    }

    private void aboutItem() {
    }

    public MenubarController(MenubarViewModel menubarViewModel) {
        this.menubarViewModel = menubarViewModel;
        mediator = MediatorFactory.getMediator();
    }
}
