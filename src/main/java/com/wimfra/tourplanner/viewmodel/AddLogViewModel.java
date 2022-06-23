package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.businesslayer.ManageTourLogServiceImpl;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class AddLogViewModel {
    private final StringProperty difficulty = new SimpleStringProperty();
    private final StringProperty rating = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty totalTime = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();

    private JavaAppManager appManager = JavaAppManagerFactory.GetManager();

    private ManageTourLogServiceImpl manageTourLogService = new ManageTourLogServiceImpl();


    public StringProperty difficultyProperty() {return difficulty;}

    public StringProperty ratingProperty() {return rating;
    }

    public StringProperty dateProperty() { return date;}

    public StringProperty timeProperty() { return time;}

    public StringProperty totalTimeProperty() { return totalTime;
    }


    public StringProperty commentProperty() { return comment;
    }

    public void addNewLog(String dif, String rat, int tourID) {

            List<String> data = new ArrayList();
            data.add(0, dateProperty().get());
            data.add(1, timeProperty().get());
            data.add(2, commentProperty().get());
            data.add(3, dif);
            data.add(4, totalTimeProperty().get());
            data.add(5, rat);
            data.add(6, String.valueOf(tourID));
            manageTourLogService.addNewLog(data);
        }
    }
