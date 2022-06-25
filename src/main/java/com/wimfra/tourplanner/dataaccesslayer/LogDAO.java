package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.models.LogModel;
import com.wimfra.tourplanner.models.Tour;

import java.util.List;

public class LogDAO {
    private DataAccess databaseAccess;

    public LogDAO() {
        databaseAccess = DBService.getInstance();
    }

    public List<LogModel> GetLogs() {
        return databaseAccess.getLogs();
    }

    public void AddNewLog(List<String> data) {
        databaseAccess.addNewLog(data);
    }

    public void DeleteLog(int logID) {
        databaseAccess.deleteLog(logID);
    }

    public List<String> GetSingleLog(int logID) {
        return databaseAccess.getSingleLog(logID);
    }

    public void EditLogData(List<String> data, int logID) {
        databaseAccess.editLogData(data, logID);
    }

    public List<LogModel> getAllLogsFromSingleTour(int tourID) {
        return databaseAccess.getAllLogsFromSingleTour(tourID);
    }
}
