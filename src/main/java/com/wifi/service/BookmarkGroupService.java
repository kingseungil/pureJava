package com.wifi.service;

import com.wifi.dao.BookmarkGroupDao;
import com.wifi.model.BookmarkGroup;
import java.sql.SQLException;
import java.util.List;

public class BookmarkGroupService {

    private BookmarkGroupDao bookmarkGroupDao = new BookmarkGroupDao();

    public void addBookmarkGroup(String name, int rank) throws SQLException {
        List<BookmarkGroup> bookmarkGroupList = bookmarkGroupDao.getBookmarkGroupList();

        int maxRank = 0;
        for (BookmarkGroup bookmarkGroup : bookmarkGroupList) {
            maxRank = Math.max(maxRank, bookmarkGroup.getRank());
        }

        if (rank > maxRank + 1) {
            rank = maxRank + 1;
        }

        // 순서를 위한 rank 값이 중복되는 경우
        for (BookmarkGroup bookmarkGroup : bookmarkGroupList) {
            if (bookmarkGroup.getRank() >= rank) {
                bookmarkGroup.setRank(bookmarkGroup.getRank() + 1);
                bookmarkGroupDao.updateBookmarkGroupRank(bookmarkGroup);
            }
        }

        bookmarkGroupDao.addBookmarkGroup(name, rank);
    }
}
