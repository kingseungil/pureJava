package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            System.out.println("connection = " + connection);

            // test table 불러오기
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM test";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                System.out.println("id = " + id);
                System.out.println("name = " + name);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLiteUtil.close(connection);
        }
    }

}
