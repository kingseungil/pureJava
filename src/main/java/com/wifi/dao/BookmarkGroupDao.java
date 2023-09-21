package com.wifi.dao;

import com.wifi.model.BookmarkGroup;
import com.wifi.util.SQLiteConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookmarkGroupDao {

    private static final Logger logger = LoggerFactory.getLogger(BookmarkGroupDao.class);

    public List<BookmarkGroup> getBookmarkGroupList() {
        List<BookmarkGroup> bookmarkGroupList = new ArrayList<>();
        String query = "SELECT * FROM bookmark_group order by rank";

        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                BookmarkGroup bookmarkGroup = new BookmarkGroup();
                bookmarkGroup.setId(resultSet.getInt("id"));
                bookmarkGroup.setName(resultSet.getString("name"));
                bookmarkGroup.setRank(resultSet.getInt("rank"));
                bookmarkGroup.setCreated_date(resultSet.getString("created_date"));
                bookmarkGroup.setUpdated_date(resultSet.getString("updated_date"));
                bookmarkGroupList.add(bookmarkGroup);
            }

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Failed to get the list of bookmark groups", e);
        }

        return bookmarkGroupList;
    }

    public void addBookmarkGroup(String name, int rank) {
        String query = "INSERT INTO bookmark_group (name, rank, created_date) VALUES (?, ?, datetime('now','localtime'))";

        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, rank);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Failed to add bookmark group", e);
        }
    }

    public void updateBookmarkGroupRank(BookmarkGroup bookmarkGroup) {
        String query = "UPDATE bookmark_group SET name = ?, rank = ? WHERE id = ?";

        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, bookmarkGroup.getName());
            preparedStatement.setInt(2, bookmarkGroup.getRank());
            preparedStatement.setInt(3, bookmarkGroup.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Failed to update bookmark group rank", e);
        }
    }
}
