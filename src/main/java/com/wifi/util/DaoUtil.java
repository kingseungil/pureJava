package com.wifi.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoUtil {

    private static final Logger logger = LoggerFactory.getLogger(DaoUtil.class);

    public static <T> List<T> executeQuery(String query, ResultSetHandler<T> handler, Object... params) {
        List<T> results = new ArrayList<>();
        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, params);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(handler.handle(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to execute query", e);
        }
        return results;
    }

    public static void executeUpdate(String query, Object... params) {
        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, params);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to execute update", e);
        }
    }

    public static void executeBatchUpdate(String query, List<Object[]> paramsList) {
        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Object[] params : paramsList) {
                setParameters(preparedStatement, params);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            logger.error("Failed to execute batch update", e);
        }
    }


    private static void setParameters(PreparedStatement preparedStatement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }

    @FunctionalInterface
    public interface ResultSetHandler<T> {

        T handle(ResultSet rs) throws SQLException;
    }
}
