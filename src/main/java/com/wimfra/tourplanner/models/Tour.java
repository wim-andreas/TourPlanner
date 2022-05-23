package com.wimfra.tourplanner.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
public class Tour {

    @Getter
    @Setter
    private int tour_id;

    @Getter
    @Setter
    private String tour_name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String from_where;

    @Getter
    @Setter
    private String to_where;

    @Getter
    @Setter
    private String transportation;

    @Getter
    @Setter
    private double distance;

    @Getter
    @Setter
    private String duration;

    @Getter
    @Setter
    private String route_info;






}
