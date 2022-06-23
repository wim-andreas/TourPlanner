package com.wimfra.tourplanner.mediator;

import lombok.Getter;
import lombok.Setter;

public class MediatorImpl implements Mediator{

    //default constructor
    public MediatorImpl() {
    }

    private int tour_id;

    private int log_id;

    @Override
    public void setTourID(int tour_id) {
        this.tour_id = tour_id;
    }

    @Override
    public int getTourID() {
        return tour_id;
    }

    @Override
    public void setLogID(int log_id) {
        this.log_id = log_id;
    }

    @Override
    public int getLogID() {
        return log_id;
    }
}
