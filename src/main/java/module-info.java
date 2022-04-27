module com.wimfra.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.wimfra.tourplanner to javafx.fxml;
    exports com.wimfra.tourplanner;
}