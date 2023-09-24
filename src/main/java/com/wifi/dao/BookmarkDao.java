package com.wifi.dao;

import com.wifi.dto.response.BookmarkResponseDTO;
import com.wifi.util.DaoUtil;
import java.util.List;

public class BookmarkDao {

    public List<BookmarkResponseDTO> getBookmarkList() {
        String query = "SELECT b.id, b.bookmark_group_id, bg.name as group_name, b.roadAdd, b.date "
          + "FROM bookmark b " + "JOIN bookmark_group bg ON b.bookmark_group_id = bg.id ";
        return DaoUtil.executeQuery(query, rs -> {
            BookmarkResponseDTO bookmark = new BookmarkResponseDTO();
            bookmark.setId(rs.getInt("id"));
            bookmark.setBookmark_group_id(rs.getInt("bookmark_group_id"));
            bookmark.setGroup_name(rs.getString("group_name"));
            bookmark.setRoadAdd(rs.getString("roadAdd"));
            bookmark.setDate(rs.getString("date"));
            return bookmark;
        });
    }

    public BookmarkResponseDTO getBookmark(int id) {
        String query = "SELECT b.id, b.bookmark_group_id, bg.name as group_name, b.roadAdd, b.date "
          + "FROM bookmark b " + "JOIN bookmark_group bg ON b.bookmark_group_id = bg.id " + "WHERE b.id = ?";
        return DaoUtil.executeQuery(query, rs -> {
            BookmarkResponseDTO bookmark = new BookmarkResponseDTO();
            bookmark.setId(rs.getInt("id"));
            bookmark.setBookmark_group_id(rs.getInt("bookmark_group_id"));
            bookmark.setGroup_name(rs.getString("group_name"));
            bookmark.setRoadAdd(rs.getString("roadAdd"));
            bookmark.setDate(rs.getString("date"));
            return bookmark;
        }, id).get(0);
    }

    public void deleteBookmark(int id) {
        String query = "DELETE FROM bookmark WHERE id = ?";
        DaoUtil.executeUpdate(query, id);
    }
}
