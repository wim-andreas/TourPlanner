module com.wimfra.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.wimfra.tourplanner to javafx.fxml;
    exports com.wimfra.tourplanner;
    exports com.wimfra.tourplanner.view;
    opens com.wimfra.tourplanner.view to javafx.fxml;
}