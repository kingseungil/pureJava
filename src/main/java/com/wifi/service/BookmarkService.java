package com.wifi.service;

import com.wifi.dao.BookmarkDao;
import com.wifi.dto.response.BookmarkResponseDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookmarkService {

    private static final Logger logger = LoggerFactory.getLogger(BookmarkService.class);
    private final BookmarkDao bookmarkDao = new BookmarkDao();

    public void addBookmark() {
    }

    public void deleteBookmark(int id) {
        bookmarkDao.deleteBookmark(id);
    }

    public BookmarkResponseDTO getBookmark(int id) {
        return bookmarkDao.getBookmark(id);
    }

    public List<BookmarkResponseDTO> getBookmarkList() {
        return bookmarkDao.getBookmarkList();
    }

}
