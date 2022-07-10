package com.wimfra.tourplanner.businesslayer.mapquest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapQuestAPITest {

    //WIEN - GRAZ
    private final String DISTANCE = "120.009";
    private final String TIME = "7192.0";

    //SALZBURG - LINZ
    private final String DISTANCE2 = "85.378";
    private final String TIME2 = "4826.0";

    @Test
    public void getDistanceWGTest(){
        Map<String, Object> data = MapQuestAPI.getDirections("Wien", "Graz");
        assertEquals(data.get("distance").toString(), DISTANCE);
    }

    @Test
    public void getTimeWGTest(){
        Map<String, Object> data = MapQuestAPI.getDirections("Wien", "Graz");
        assertEquals(data.get("time").toString(), TIME);
    }

    @Test
    public void getDistanceSLTest(){
        Map<String, Object> data = MapQuestAPI.getDirections("Salzburg", "Linz");
        assertEquals(data.get("distance").toString(), DISTANCE2);
    }

    @Test
    public void getTimeSLTest(){
        Map<String, Object> data = MapQuestAPI.getDirections("Salzburg", "Linz");
        assertEquals(data.get("time").toString(), TIME2);
    }

}
