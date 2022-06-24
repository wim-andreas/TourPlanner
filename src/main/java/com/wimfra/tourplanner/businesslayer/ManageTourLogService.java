package com.wimfra.tourplanner.businesslayer;

import com.wimfra.tourplanner.models.LogModel;

import java.util.List;

public interface ManageTourLogService {

    List<LogModel> getAllLogs();

    void addNewLog(List<String> data);

    void deleteLog(int logID);

    List<String> getSingleLog(int tourID);

    void editLogData(List<String> data, int logID);

    List<LogModel> search(String value, boolean b);
}
