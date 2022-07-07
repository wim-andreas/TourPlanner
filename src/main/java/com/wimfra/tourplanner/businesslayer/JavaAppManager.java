package com.wimfra.tourplanner.businesslayer;

public interface JavaAppManager {
    void addTourWindow();

    void editTourWindow(int tourID);

    void addLogWindow(int tourID);

    void editLogWindow(int logID);

    void clickMeWindow();
}
