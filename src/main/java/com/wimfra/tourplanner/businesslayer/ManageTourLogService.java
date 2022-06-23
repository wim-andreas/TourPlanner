package com.wimfra.tourplanner.businesslayer;

import com.wimfra.tourplanner.models.LogModel;

import java.util.List;

public interface ManageTourLogService {

    List<LogModel> getAllLogs();

    void addNewLog(List<String> data);
}
