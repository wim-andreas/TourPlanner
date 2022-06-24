package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.models.LogModel;
import com.wimfra.tourplanner.models.Tour;

import java.util.List;

public interface DataAccess {
    List<Tour> getTours();

    Tour getSingleTour(int id);

    Tour addNewTour(List<String> data);

    boolean deleteTour(int tour_id);

    boolean editTourData(List<String> data, int id);

    List<LogModel> getLogs();

    LogModel addNewLog(List<String> data);

    boolean deleteLog(int logID);

    List<String> getSingleLog(int tourID);

    boolean editLogData(List<String> data, int logID);
}
