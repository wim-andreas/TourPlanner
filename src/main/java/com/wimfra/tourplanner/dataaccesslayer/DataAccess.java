package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.models.LogModel;
import com.wimfra.tourplanner.models.TourModel;

import java.util.List;

public interface DataAccess {
    List<TourModel> getTours();

    TourModel getSingleTour(int id);

    int addNewTour(List<String> data);

    boolean deleteTour(int tour_id);

    boolean editTourData(List<String> data, int id);

    List<LogModel> getLogs();

    LogModel addNewLog(List<String> data);

    boolean deleteLog(int logID);

    boolean deleteLogAfterTourDelete(int tourID);

    List<String> getSingleLog(int tourID);

    boolean editLogData(List<String> data, int logID);

    List<LogModel> getAllLogsFromSingleTour(int tourID);

}
