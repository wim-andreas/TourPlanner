package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.models.LogModel;
import com.wimfra.tourplanner.models.Tour;

import java.util.List;

public class LogDAO {
    private DataAccess databaseAccess;

    public LogDAO(){
        databaseAccess = new DBService();
    }

    public List<LogModel> GetLogs(){
        return databaseAccess.getLogs();
    }

    public void AddNewLog(List<String> data) { databaseAccess.addNewLog(data);
    }
}
