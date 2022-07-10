package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.configuration.AppConfiguration;
import com.wimfra.tourplanner.models.LogModel;
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


public class LogServiceTest {
    private static DBService dbService;

    private static List<String> data = new ArrayList<>();
    private static List<String> data2 = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        dbService = DBService.getInstance();
        createLogData();
    }

    public static void createLogData() {
        data.add(0, "13-04-2022");
        data.add(1, "13:33:33");
        data.add(2, "WOW");
        data.add(3, "hard");
        data.add(4, "1300");
        data.add(5, "4");
    }



    @BeforeEach
    public void setUpTestLog(){
        try {
            Connection connection = DBService.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO logs(log_id, tour_id, date_, time_, comment_, difficulty, total_time, rating) VALUES(?,?,?,?,?,?,?,?);");
            preparedStatement.setInt(1, -1);
            preparedStatement.setInt(2, -1);
            preparedStatement.setString(3, data.get(0));
            preparedStatement.setString(4, data.get(1));
            preparedStatement.setString(5, data.get(2));
            preparedStatement.setString(6, data.get(3));
            preparedStatement.setString(7, data.get(4));
            preparedStatement.setString(8, (data.get(5)));
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }

    }

    @Test
    public void getSingleLogTest(){
        List<String> logdata = dbService.getSingleLog(-1);

        assertEquals("13-04-2022", logdata.get(0));
        assertEquals("13:33:33", logdata.get(1));
        assertEquals("hard", logdata.get(2));
        assertEquals("4", logdata.get(3));
        assertEquals("WOW", logdata.get(4));
        assertEquals("1300", logdata.get(5));
    }

    @Test
    public void deleteLogTest(){
        dbService.deleteLog(-1);
        assertEquals(null, dbService.getSingleTour(-1));
    }

    @Test
    public void editLogTest(){
        dbService.editLogData(data2,-1);

        List<String> logdata = dbService.getSingleLog(-1);

        assertEquals("03-05-2021", logdata.get(0));
        assertEquals("12:23:33", logdata.get(1));
        assertEquals("easy", logdata.get(2));
        assertEquals("1", logdata.get(3));
        assertEquals("OWO", logdata.get(4));
        assertEquals("12", logdata.get(5));


    }

    @AfterAll
    public static void cleanLogs(){
        try {
            Connection connection = DBService.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from logs WHERE log_id < ?");
            preparedStatement.setInt(1, 0);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
