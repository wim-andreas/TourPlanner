package com.wimfra.tourplanner.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
public class Tour {

    public Tour(String tour_name) {
        this.tour_name = tour_name;
    }

    public Tour(int tour_id, String tour_name, String description, String from_where, String to_where, String transportation, double distance, String duration, String route_info) {
        this.tour_id = tour_id;
        this.tour_name = tour_name;
        this.description = description;
        this.from_where = from_where;
        this.to_where = to_where;
        this.transportation = transportation;
        this.distance = distance;
        this.duration = duration;
        this.route_info = route_info;
    }

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
