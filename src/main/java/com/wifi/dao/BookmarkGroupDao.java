package com.wifi.dao;

import com.wifi.model.BookmarkGroup;
import com.wifi.util.DaoUtil;
import java.util.List;

public class BookmarkGroupDao {


    public List<BookmarkGroup> getBookmarkGroupList() {
        String query = "SELECT * FROM bookmark_group order by rank";
        return DaoUtil.executeQuery(query, rs -> {
            BookmarkGroup bookmarkGroup = new BookmarkGroup();
            bookmarkGroup.setId(rs.getInt("id"));
            bookmarkGroup.setName(rs.getString("name"));
            bookmarkGroup.setRank(rs.getInt("rank"));
            bookmarkGroup.setCreated_date(rs.getString("created_date"));
            bookmarkGroup.setUpdated_date(rs.getString("updated_date"));
            return bookmarkGroup;
        });
    }

    public void addBookmarkGroup(String name, int rank) {
        String query = "INSERT INTO bookmark_group (name, rank, created_date) VALUES (?, ?, datetime('now','localtime'))";
        DaoUtil.executeUpdate(query, name, rank);
    }

    public void updateBookmarkGroupRank(BookmarkGroup bookmarkGroup) {
        String query = "UPDATE bookmark_group SET rank = ? WHERE id = ?";
        DaoUtil.executeUpdate(query, bookmarkGroup.getRank(), bookmarkGroup.getId());
    }

    public void deleteBookmark(int id) {
        String query = "DELETE FROM bookmark_group WHERE id = ?";
        DaoUtil.executeUpdate(query, id);
    }

    public BookmarkGroup getBookmarkGroupById(int id) {
        String query = "SELECT * FROM bookmark_group WHERE id = ?";
        List<BookmarkGroup> result = DaoUtil.executeQuery(query, rs -> {
            BookmarkGroup bookmarkGroup = new BookmarkGroup();
            bookmarkGroup.setId(rs.getInt("id"));
            bookmarkGroup.setName(rs.getString("name"));
            bookmarkGroup.setRank(rs.getInt("rank"));
            bookmarkGroup.setCreated_date(rs.getString("created_date"));
            bookmarkGroup.setUpdated_date(rs.getString("updated_date"));
            return bookmarkGroup;
        }, id);
        return result.isEmpty() ? null : result.get(0);
    }

    public void updateBookmarkGroup(int id, String name, int rank) {
        String query = "UPDATE bookmark_group SET name = ?, rank = ?, updated_date = datetime('now','localtime') WHERE id = ?";
        DaoUtil.executeUpdate(query, name, rank, id);
    }
}
