module com.wimfra.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;


    opens com.wimfra.tourplanner to javafx.fxml;
    opens com.wimfra.tourplanner.models to javafx.fxml;
    opens com.wimfra.tourplanner.view to javafx.fxml;

    exports com.wimfra.tourplanner;
    exports com.wimfra.tourplanner.view;
    exports com.wimfra.tourplanner.models;



}