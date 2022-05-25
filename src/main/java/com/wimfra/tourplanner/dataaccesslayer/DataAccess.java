package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.models.Tour;

import java.util.List;

public interface DataAccess {
    public List<Tour> getTours();

}
