package util;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLiteConnection {

    public static class SQLiteUtil {

        private static final String DB_NAME = "db.sqlite";

        public static Connection getConnection() throws SQLException {
            return java.sql.DriverManager.getConnection("jdbc:sqlite:src/main/resources/" + DB_NAME);
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

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = SQLiteUtil.getConnection();
            System.out.println("db 연결 성공");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLiteUtil.close(connection);
        }
    }

}
