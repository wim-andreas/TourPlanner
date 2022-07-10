package com.wimfra.tourplanner.dataaccesslayer;

import com.wimfra.tourplanner.businesslayer.parsing.ParserService;
import com.wimfra.tourplanner.businesslayer.parsing.ParserServiceImpl;
import com.wimfra.tourplanner.configuration.AppConfiguration;
import com.wimfra.tourplanner.configuration.AppConfigurationLoader;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.models.LogModel;
import com.wimfra.tourplanner.models.TourModel;
import com.wimfra.tourplanner.view.AddLogController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService implements DataAccess {
    private static DBService instance;
    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PW;
    private final ParserService parserService;
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(DBService.class);

    public DBService(AppConfiguration appConfiguration, ParserService parserService) {
        this.parserService = parserService;
        DB_URL = appConfiguration.getDbUrl();
        DB_USER = appConfiguration.getDbUsername();
        DB_PW = appConfiguration.getDbPassword();
    }

    public static DBService getInstance() {
        if (DBService.instance == null) {
            DBService.instance = new DBService(AppConfigurationLoader.getInstance().getAppConfiguration(), new ParserServiceImpl());
        }
        return DBService.instance;
    }

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
            logger.debug("Database connection successfully established!");
            return connection;
        } catch (SQLException e) {
            logger.fatal(e.getStackTrace().toString());
            e.printStackTrace();
        }
        return null;
    }

    private void closeConnection(Connection connection){
        try {
            connection.close();
            logger.debug("Database connection successfully closed!");
        } catch (SQLException e) {
            logger.fatal(e.getStackTrace().toString());
            e.printStackTrace();
        }
    }

    @Override
    public List<TourModel> getTours() {
        try {
            Connection connection = getConnection();
            logger.debug("Getting all tours from the database..");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT tour_id, tour_name, description, from_where, to_where, transportation, distance, duration, route_info FROM tours;");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TourModel> allTours = new ArrayList<>();
            while (resultSet.next()) {
                allTours.add(new TourModel(
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
            closeConnection(connection);
            return allTours;
        } catch (SQLException e) {
            logger.error(e.getStackTrace().toString());
        }
        return null;

    }



    @Override
    public TourModel getSingleTour(int id) {
        try {
            Connection connection = getConnection();
            logger.debug("Getting a (specific) single tour from the database..");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT tour_id, tour_name, description, from_where, to_where, transportation, distance, duration, route_info FROM tours where tour_id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                TourModel tour = TourModel.builder()
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
                closeConnection(connection);
                return tour;
            }
        } catch (SQLException e) {
            logger.error(e.getStackTrace().toString());
        }
        return null;
    }

    @Override
    public int addNewTour(List<String> data) {
        int id = 0;

        try {
            Connection connection = getConnection();
            logger.debug("Creating new tour in the database..");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tours(tour_name, description, from_where, to_where, transportation, distance, duration, route_info) VALUES(?,?,?,?,?,?,?,?) ", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, data.get(0));
            preparedStatement.setString(2, data.get(1));
            preparedStatement.setString(3, data.get(2));
            preparedStatement.setString(4, data.get(3));
            preparedStatement.setString(5, data.get(4));
            preparedStatement.setDouble(6, parserService.parseStringIntoDouble(data.get(5)));
            preparedStatement.setString(7, data.get(6));
            preparedStatement.setString(8, data.get(7));

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int id1 = resultSet.getInt(1);

            preparedStatement.close();
            closeConnection(connection);

            return id1;
        } catch (SQLException e) {
            logger.error(e.getStackTrace().toString());
        }
        return id;
    }

    @Override
    public boolean deleteTour(int tour_id) {
        deleteLogAfterTourDelete(tour_id);
        try {
            Connection connection = getConnection();
            logger.debug("Deleting a single tour from the database..");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tours WHERE tour_id = ?;");
            preparedStatement.setInt(1, tour_id);

            int rows = preparedStatement.executeUpdate();

            preparedStatement.close();
            closeConnection(connection);

            if (rows == 0) {
                return false;
            }

            preparedStatement.close();
            closeConnection(connection);

            return true;
        } catch (SQLException e) {
            logger.error(e.getStackTrace().toString());
        }
        return false;

    }

    @Override
    public boolean editTourData(List<String> data, int id) {
        try {
            Connection connection = getConnection();
            logger.debug("Editing a specific single tour in the database..");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tours SET tour_name = ?, description= ?, from_where = ?, to_where = ?, transportation = ?,  route_info = ?  WHERE tour_id = ?;");

            preparedStatement.setString(1, data.get(0));
            preparedStatement.setString(2, data.get(1));
            preparedStatement.setString(3, data.get(2));
            preparedStatement.setString(4, data.get(3));
            preparedStatement.setString(5, data.get(4));
            preparedStatement.setString(6, data.get(5));
            preparedStatement.setInt(7, id);


            int rows = preparedStatement.executeUpdate();

            preparedStatement.close();
            closeConnection(connection);

            if (rows == 0) {
                return false;
            }

            return true;
        } catch (SQLException e) {
            logger.error(e.getStackTrace().toString());
        }

        return true;
    }

    @Override
    public List<LogModel> getLogs() {
        try {
            Connection connection = getConnection();
            logger.debug("Getting all logs from the database..");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT log_id, tour_id , date_, time_, difficulty, rating, comment_,  total_time FROM logs;");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<LogModel> allLogs = new ArrayList<>();
            while (resultSet.next()) {
                allLogs.add(new LogModel(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        getSingleTour(resultSet.getInt(2)).getTour_name(),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getString(8)

                ));
            }
            resultSet.close();
            preparedStatement.close();
            closeConnection(connection);
            return allLogs;
        } catch (SQLException e) {
            logger.error(e.getStackTrace().toString());
        }
        return null;
    }

    @Override
    public LogModel addNewLog(List<String> data) {
        try {
            Connection connection = getConnection();
            logger.debug("Creating a new log in the database..");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO logs(tour_id, date_, time_, comment_, difficulty, total_time, rating) VALUES(?,?,?,?,?,?,?);");
            preparedStatement.setInt(1, Integer.parseInt(data.get(6)));
            preparedStatement.setString(2, data.get(0));
            preparedStatement.setString(3, data.get(1));
            preparedStatement.setString(4, data.get(2));
            preparedStatement.setString(5, data.get(3));
            preparedStatement.setString(6, data.get(4));
            preparedStatement.setString(7, (data.get(5)));
            preparedStatement.execute();
            preparedStatement.close();
            closeConnection(connection);
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean deleteLog(int logID) {
        try {
            Connection connection = getConnection();
            logger.debug("Deleting a specific log from the database..");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM logs WHERE log_id = ?;");
            preparedStatement.setInt(1, logID);

            int rows = preparedStatement.executeUpdate();

            preparedStatement.close();
            closeConnection(connection);

            if (rows == 0) {
                return false;
            }

            preparedStatement.close();
            closeConnection(connection);

            return true;
        } catch (SQLException e) {
            logger.error(e.getStackTrace().toString());
        }
        return false;

    }

    @Override
    public boolean deleteLogAfterTourDelete(int tourID) {
        try {
            //TODO: Pascal fragen, ob diese Funktion tatsächlich notwendig ist -> ON DELETE CASCADE IS ACTIVATED
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM logs WHERE tour_id = ?;");
            preparedStatement.setInt(1, tourID);

            int rows = preparedStatement.executeUpdate();

            preparedStatement.close();
            closeConnection(connection);

            if (rows == 0) {
                return false;
            }

            preparedStatement.close();
            closeConnection(connection);

            return true;
        } catch (SQLException e) {
            logger.error(e.getStackTrace().toString());
        }
        return false;

    }

    @Override
    public List<String> getSingleLog(int logID) {
        try {
            Connection connection = getConnection();
            logger.debug("Getting a (specific) single log from the database..");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT date_, time_, difficulty, rating, comment_,  total_time, tour_id FROM logs where log_id = ?;");
            preparedStatement.setInt(1, logID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                List<String> data = new ArrayList<>();
                data.add(0, resultSet.getString(1));
                data.add(1, resultSet.getString(2));
                data.add(2, resultSet.getString(3));
                data.add(3, String.valueOf(resultSet.getInt(4)));
                data.add(4, resultSet.getString(5));
                data.add(5, resultSet.getString(6));
                data.add(6, resultSet.getString(7));
                resultSet.close();
                preparedStatement.close();
                closeConnection(connection);
                return data;
            }

        } catch (SQLException e) {
            logger.error(e.getStackTrace().toString());
        }
        return null;
    }

    @Override
    public boolean editLogData(List<String> data, int logID) {
        try {
            Connection connection = getConnection();
            logger.debug("Editing a single log in the database..");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE logs SET date_ = ?, time_= ?, difficulty = ?, rating = ?, comment_ = ?, total_time = ?  WHERE log_id = ?;");

            preparedStatement.setString(1, data.get(0));
            preparedStatement.setString(2, data.get(1));
            preparedStatement.setString(3, data.get(2));
            preparedStatement.setString(4, data.get(3));
            preparedStatement.setString(5, data.get(4));
            preparedStatement.setString(6, data.get(5));
            preparedStatement.setInt(7, logID);


            int rows = preparedStatement.executeUpdate();

            preparedStatement.close();
            closeConnection(connection);

            if (rows == 0) {
                return false;
            }

            return true;
        } catch (SQLException e) {
            logger.error(e.getStackTrace().toString());
        }

        return true;
    }

    @Override
    public List<LogModel> getAllLogsFromSingleTour(int tourID) {
        try {
            Connection connection = getConnection();
            logger.debug("Getting all logs for a specific tour from the database..");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT log_id, tour_id , date_, time_, difficulty, rating, comment_, total_time FROM logs WHERE tour_id = ?;");
            preparedStatement.setInt(1, tourID);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<LogModel> allLogs = new ArrayList<>();
            while (resultSet.next()) {
                allLogs.add(new LogModel(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        getSingleTour(resultSet.getInt(2)).getTour_name(),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                ));
            }
            resultSet.close();
            preparedStatement.close();
            closeConnection(connection);
            return allLogs;
        } catch (SQLException e) {
            logger.error(e.getStackTrace().toString());
        }
        return null;
    }



    }


