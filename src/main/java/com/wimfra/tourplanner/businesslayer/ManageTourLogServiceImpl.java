package com.wimfra.tourplanner.businesslayer;

import com.wimfra.tourplanner.dataaccesslayer.LogDAO;
import com.wimfra.tourplanner.models.LogModel;

import java.util.List;

public class ManageTourLogServiceImpl implements ManageTourLogService {
private final LogDAO logDAO = new LogDAO();
    @Override
    public List<LogModel> getAllLogs() {
        return logDAO.GetLogs();
    }

    @Override
    public void addNewLog(List<String> data) {
        logDAO.AddNewLog(data);
    }
}
