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

    //TODO: Adjust prepared Statements to the correct datatypes - right now they are not working properly.

    @Override
    public List<Tour> getTours() {
        try{
            Connection connection = DBService.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT tour_id, tour_name, description, from_where, to_where, transportation, distance, duration, route_info FROM tours;");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Tour> allTours = new ArrayList<>();
            while (resultSet.next()) {
                allTours.add(new Tour(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                        ));
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

    @Override
    public Tour getSingleTour(int id) {
        try{
            Connection connection = DBService.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT tour_id, tour_name, description, from_where, to_where, transportation, distance, duration, route_info FROM tours where tour_id = ?;");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Tour tour =  Tour.builder()
                        .tour_id(resultSet.getInt(1))
                        .tour_name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .from_where(resultSet.getString(4))
                        .to_where(resultSet.getString(5))
                        .transportation(resultSet.getString(6))
                        .distance(resultSet.getString(7))
                        .duration(resultSet.getString(8))
                        .route_info(resultSet.getString(9))
                        .build();
                resultSet.close();
                preparedStatement.close();
                connection.close();
                return tour;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Tour addNewTour(List<String> data) {
        try {
            Connection connection = DBService.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tours(tour_name, description, from_where, to_where, transportation, distance, duration, route_info) VALUES(?,?,?,?,?,?,?,?);");
            preparedStatement.setString(1, data.get(0));
            preparedStatement.setString(2, data.get(1));
            preparedStatement.setString(3, data.get(2));
            preparedStatement.setString(4, data.get(3));
            preparedStatement.setString(5, data.get(4));
            preparedStatement.setString(6, data.get(5));
            preparedStatement.setString(7, data.get(6));
            preparedStatement.setString(8, data.get(7));

            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteTour(int tour_id) {
        try {
            Connection connection = DBService.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tours WHERE tour_id = ?;");
            preparedStatement.setInt(1, tour_id);

            int rows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (rows == 0) {
                return false;
            }

            preparedStatement.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean editTourData(List<String> data, int id) {
        try {
            Connection connection = DBService.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tours SET tour_name = ?, description= ?, from_where = ?, to_where = ?, transportation = ?, distance = ?, duration = ?, route_info = ?  WHERE tour_id = ?;");

            preparedStatement.setString(1, data.get(0));
            preparedStatement.setString(2, data.get(1));
            preparedStatement.setString(3, data.get(2));
            preparedStatement.setString(4, data.get(3));
            preparedStatement.setString(5, data.get(4));
            preparedStatement.setString(6, data.get(5));
            preparedStatement.setString(7, data.get(6));
            preparedStatement.setString(8, data.get(7));
            preparedStatement.setInt(9,id);


            int rows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (rows == 0) {
                return false;
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}