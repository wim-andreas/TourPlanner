package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.models.LogModel;

import java.util.List;

public class LogDAO {
    private DataAccess databaseAccess;

    public LogDAO() {
        databaseAccess = DBService.getInstance();
    }

    public List<LogModel> getLogs() {
        return databaseAccess.getLogs();
    }

    public void addNewLog(List<String> data) {
        databaseAccess.addNewLog(data);
    }

    public void deleteLog(int logID) {
        databaseAccess.deleteLog(logID);
    }

    public List<String> getSingleLog(int logID) {
        return databaseAccess.getSingleLog(logID);
    }

    public void editLogData(List<String> data, int logID) {
        databaseAccess.editLogData(data, logID);
    }

    public List<LogModel> getAllLogsFromSingleTour(int tourID) {
        return databaseAccess.getAllLogsFromSingleTour(tourID);
    }
}
