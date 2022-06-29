package com.wimfra.tourplanner.dataaccesslayer;


import com.wimfra.tourplanner.models.TourModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourServiceTest {

    private static DBService dbService;

    private static List<String> data = new ArrayList<>();
    private static List<String> data2 = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        dbService = new DBService();
        createTourData();
    }

    public static void createTourData() {
        data.add(0, "Testtour");
        data.add(1, "Testtour description");
        data.add(2, "Wien");
        data.add(3, "Graz");
        data.add(4, "Auto");
        data.add(5, "1300");
        data.add(6, "200");
        data.add(7, "hard");

        data2.add(0, "Testtour 2");
        data2.add(1, "Testtour description 2");
        data2.add(2, "Linz");
        data2.add(3, "Salzburg");
        data2.add(4, "Flugzeug");
        data2.add(5, "100");
        data2.add(6, "20");
        data2.add(7, "easy");
    }

    @BeforeEach
    public void setUpTestTour() {
        try {
            Connection connection = DBService.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tours(tour_id, tour_name, description, from_where, to_where, transportation, distance, duration, route_info) VALUES(?,?,?,?,?,?,?,?,?);");
            preparedStatement.setInt(1, -1);
            preparedStatement.setString(2, data.get(0));
            preparedStatement.setString(3, data.get(1));
            preparedStatement.setString(4, data.get(2));
            preparedStatement.setString(5, data.get(3));
            preparedStatement.setString(6, data.get(4));
            preparedStatement.setDouble(7, Double.valueOf(data.get(5)));
            preparedStatement.setString(8, data.get(6));
            preparedStatement.setString(9, data.get(7));
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
    }


    @Test
    public void getSingleTourTest() {
        TourModel tour = dbService.getSingleTour(-1);
        assertEquals("Testtour", tour.getTour_name());
        assertEquals("Testtour description", tour.getDescription());
        assertEquals("Wien", tour.getFrom_where());
        assertEquals("Graz", tour.getTo_where());
        assertEquals("Auto", tour.getTransportation());
    }

    @Test
    public void deleteTourTest() {
        dbService.deleteTour(-1);
        assertEquals(null, dbService.getSingleTour(-1));
    }

    @Test
    public void editTourTest(){
        dbService.editTourData(data2, -1);
        TourModel tour = dbService.getSingleTour(-1);

        assertEquals("Testtour 2", tour.getTour_name());
        assertEquals("Testtour description 2", tour.getDescription());
        assertEquals("Linz", tour.getFrom_where());
        assertEquals("Salzburg", tour.getTo_where());
        assertEquals("Flugzeug", tour.getTransportation());
    }


    @AfterAll
    static void cleanTour() {
        try {
            Connection connection = DBService.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from tours WHERE tour_id < ?");
            preparedStatement.setInt(1, 0);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

