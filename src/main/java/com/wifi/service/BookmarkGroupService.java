package com.wifi.service;

import com.wifi.dao.BookmarkGroupDao;
import com.wifi.model.BookmarkGroup;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookmarkGroupService {

    private static final Logger logger = LoggerFactory.getLogger(BookmarkGroupService.class);

    private final BookmarkGroupDao bookmarkGroupDao = new BookmarkGroupDao();

    public void addBookmarkGroup(String name, int rank) {
        List<BookmarkGroup> bookmarkGroupList = bookmarkGroupDao.getBookmarkGroupList();
        rank = adjustRank(rank, bookmarkGroupList);

        // 순서를 위한 rank 값이 중복되는 경우
        updateRankForDuplicate(rank, bookmarkGroupList);

        try {
            bookmarkGroupDao.addBookmarkGroup(name, rank);
        } catch (Exception e) {
            logger.error("Failed to add bookmark group", e);
        }
    }

    public void deleteBookmarkGroup(int id) {
        int currentRank = bookmarkGroupDao.getBookmarkGroupById(id).getRank();
        bookmarkGroupDao.deleteBookmarkGroup(id);

        List<BookmarkGroup> bookmarkGroupListAfterDelete = bookmarkGroupDao.getBookmarkGroupList();
        updateRankAfterDelete(currentRank, bookmarkGroupListAfterDelete);
    }

    public void updateBookmarkGroup(int id, String name, int newRank) {
        BookmarkGroup bookmarkGroup = bookmarkGroupDao.getBookmarkGroupById(id);
        int currentRank = bookmarkGroup.getRank();
        List<BookmarkGroup> bookmarkGroups = bookmarkGroupDao.getBookmarkGroupList();
        newRank = adjustRank(newRank, bookmarkGroups);

        updateRankForUpdate(currentRank, newRank, bookmarkGroups);

        try {
            bookmarkGroupDao.updateBookmarkGroup(id, name, newRank);
        } catch (Exception e) {
            logger.error("Failed to update bookmark group", e);
        }
    }


    private int adjustRank(int rank, List<BookmarkGroup> bookmarkGroupList) {
        int maxRank = bookmarkGroupList.stream().mapToInt(BookmarkGroup::getRank).max().orElse(0);
        return Math.min(rank, maxRank);
    }

    private void updateRankForDuplicate(int rank, List<BookmarkGroup> bookmarkGroupList) {
        bookmarkGroupList.stream()
                         .filter(bg -> bg.getRank() >= rank)
                         .forEach(this::increaseRank);
    }

    private void updateRankAfterDelete(int currentRank, List<BookmarkGroup> bookmarkGroupList) {
        bookmarkGroupList.stream()
                         .filter(bg -> bg.getRank() > currentRank)
                         .forEach(this::decreaseRank);
    }

    private void updateRankForUpdate(int currentRank, int newRank, List<BookmarkGroup> bookmarkGroups) {
        bookmarkGroups.stream()
                      .filter(bg -> bg.getRank() > currentRank && bg.getRank() <= newRank)
                      .forEach(this::decreaseRank);

        bookmarkGroups.stream()
                      .filter(bg -> bg.getRank() < currentRank && bg.getRank() >= newRank)
                      .forEach(this::increaseRank);
    }

    private void increaseRank(BookmarkGroup bookmarkGroup) {
        try {
            bookmarkGroup.setRank(bookmarkGroup.getRank() + 1);
            bookmarkGroupDao.updateBookmarkGroupRank(bookmarkGroup);
        } catch (Exception e) {
            logger.error("Failed to update bookmark group rank", e);
        }
    }

    private void decreaseRank(BookmarkGroup bookmarkGroup) {
        try {
            bookmarkGroup.setRank(bookmarkGroup.getRank() - 1);
            bookmarkGroupDao.updateBookmarkGroupRank(bookmarkGroup);
        } catch (Exception e) {
            logger.error("Failed to update bookmark group rank", e);
        }
    }
}
