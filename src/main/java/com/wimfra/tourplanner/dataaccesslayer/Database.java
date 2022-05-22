package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.models.Tour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database implements DataAccess {
    private String connectionString;

    public Database() {
        //get info from config file
        connectionString = "...";
        //establish DB connection
    }
;

    @Override
    public List<Tour> GetTours() {
        //SQL Statement
        Tour[] tourItems = {
                new Tour("Item1"),
                new Tour("Item2"),
                new Tour("Another"),
                new Tour("SWE1"),
                new Tour("FHTW"),
        };
        return new ArrayList<Tour>(Arrays.asList(tourItems));    }
}

