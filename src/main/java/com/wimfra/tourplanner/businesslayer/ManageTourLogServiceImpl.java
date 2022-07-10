package com.wimfra.tourplanner.businesslayer;

import com.wimfra.tourplanner.dataaccesslayer.LogDAO;
import com.wimfra.tourplanner.models.LogModel;

import java.util.List;
import java.util.stream.Collectors;

public class ManageTourLogServiceImpl implements ManageTourLogService {
    private final LogDAO logDAO = new LogDAO();

    @Override
    public List<LogModel> getAllLogs() {
        return logDAO.getLogs();
    }

    @Override
    public List<LogModel> getAllLogsFromSingleTour(int tourID){
        return logDAO.getAllLogsFromSingleTour(tourID);
    }

    @Override
    public void addNewLog(List<String> data) {
        logDAO.addNewLog(data);
    }

    @Override
    public void deleteLog(int logID) {
        logDAO.deleteLog(logID);
    }

    @Override
    public List<String> getSingleLog(int logID) {
        return logDAO.getSingleLog(logID);
    }

    @Override
    public void editLogData(List<String> data, int logID) {
        logDAO.editLogData(data, logID);
    }

    @Override
    public List<LogModel> search(String searchValue, boolean caseSensitive) {
        List<LogModel> items = getAllLogs();
        if (searchValue != null) {
            if (caseSensitive) {
                return items
                        .stream()
                        .filter(x -> x.getTourName().contains(searchValue) || x.getDifficulty().contains(searchValue)
                                || x.getDate().contains(searchValue) || x.getComment().contains(searchValue)
                                || x.getTime().contains(searchValue) || x.getTotalTime().contains(searchValue))
                        .collect(Collectors.toList());
            }
            return items
                    .stream()
                    .filter(x -> x.getTourName().toLowerCase().contains(searchValue.toLowerCase()) || x.getDifficulty().toLowerCase().contains(searchValue.toLowerCase())
                            || x.getDate().toLowerCase().contains(searchValue.toLowerCase()) || x.getComment().toLowerCase().contains(searchValue.toLowerCase())
                            || x.getTime().toLowerCase().contains(searchValue.toLowerCase()) || x.getTotalTime().toLowerCase().contains(searchValue.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return items;
    }
}

