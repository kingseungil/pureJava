package com.wifi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {

    private static final String DB_NAME = "wifi.db";

    public static class SQLiteUtil {


        public static Connection getConnection() throws SQLException, ClassNotFoundException {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:/Users/apple/Desktop/" + DB_NAME);
        }

        public static void close(Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
