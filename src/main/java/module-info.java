module com.wimfra.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires kernel;
    requires layout;
    requires io;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens com.wimfra.tourplanner.models to javafx.fxml;
    opens com.wimfra.tourplanner.view to javafx.fxml;

    exports com.wimfra.tourplanner;
    exports com.wimfra.tourplanner.view;
    exports com.wimfra.tourplanner.models;



}