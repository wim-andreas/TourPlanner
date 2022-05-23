package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.models.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService implements DataAccess {
    private static DBService instance;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "swe2user";
    private static final String PW = "swe2pw";

    public DBService() {
    }

    public static DBService getInstance() {
        if (DBService.instance == null) {
            DBService.instance = new DBService();
        }
        return DBService.instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PW);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tour> getTours() {
        try{
            Connection connection = DBService.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT tour_id, tour_name, description, from_where, to_where, transportation, distance, duration, route_info FROM tours;");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Tour> allTours = new ArrayList<>();
            while (resultSet.next()) {
                allTours.add(Tour.builder()
                        .tour_id(resultSet.getInt(1))
                        .tour_name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .from_where(resultSet.getString(4))
                        .to_where(resultSet.getString(5))
                        .transportation(resultSet.getString(6))
                        .distance(resultSet.getDouble(7))
                        .duration(resultSet.getString(8))
                        .route_info(resultSet.getString(9))
                        .build());
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return allTours;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}