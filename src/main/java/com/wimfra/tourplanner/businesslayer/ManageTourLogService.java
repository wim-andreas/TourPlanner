package com.wimfra.tourplanner.businesslayer;

import com.wimfra.tourplanner.models.LogModel;

import java.util.List;

public interface ManageTourLogService {

    List<LogModel> getAllLogs();

    List<LogModel> getAllLogsFromSingleTour(int TourID);

    void addNewLog(List<String> data);

    void deleteLog(int logID);

    List<String> getSingleLog(int logID);

    void editLogData(List<String> data, int logID);

    List<LogModel> search(String value, boolean b);
}
