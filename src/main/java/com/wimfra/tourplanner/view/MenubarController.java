package com.wimfra.tourplanner.view;

import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.mediator.Mediator;
import com.wimfra.tourplanner.mediator.MediatorFactory;
import com.wimfra.tourplanner.viewmodel.MenubarViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.net.URL;
import java.util.ResourceBundle;

public class MenubarController implements Initializable {
    @Getter
    @Setter
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
    public MenuItem click_me_item;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.debug("Setting up 'menubar-view.fxml'");
        import_item.setOnAction(actionEvent -> importItem());
        export_item.setOnAction(actionEvent -> exportItem());
        summarize_report_item.setOnAction(actionEvent -> generateSummarizeReport());
        tour_report_item.setOnAction(actionEvent -> generateTourReport());
        click_me_item.setOnAction(actionEvent -> clickMeItem());
    }

    private void importItem() {
        menubarViewModel.importTourItem(import_item.getParentPopup().getScene().getWindow());
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

    private void clickMeItem() {
        menubarViewModel.clickMeWindow();
    }

    public MenubarController(MenubarViewModel menubarViewModel) {
        this.menubarViewModel = menubarViewModel;
        mediator = MediatorFactory.getMediator();
    }
}
