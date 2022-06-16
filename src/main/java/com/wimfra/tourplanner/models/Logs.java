package com.wimfra.tourplanner.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Logs {
    @Getter
    @Setter
    private int tour_id;

    @Getter
    @Setter
    private int log_id;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private Timestamp timestamp;

    @Getter
    @Setter
    private String comment;

    @Getter
    @Setter
    private int difficulty;

    @Getter
    @Setter
    private int rating;

    @Getter
    @Setter
    private String total_time;


}
