package com.wimfra.tourplanner.models;

import lombok.Getter;
import lombok.Setter;

public class Tour {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String tourname;

    public Tour(String name){
        tourname = name;
    }


}
