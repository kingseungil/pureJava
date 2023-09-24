package com.wifi.service;

import com.wifi.dao.BookmarkDao;
import com.wifi.dao.WifiDao;
import com.wifi.dto.response.BookmarkResponseDTO;
import com.wifi.model.WifiData;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookmarkService {

    private static final Logger logger = LoggerFactory.getLogger(BookmarkService.class);
    private final BookmarkDao bookmarkDao = new BookmarkDao();
    private final WifiDao wifiDao = new WifiDao();


    public void addBookmark(int wifiId, int groupId) {
        WifiData wifiData = wifiDao.getWifiDataById(wifiId);
        String roadAdd = wifiData.getRoadAdd();

        try {
            bookmarkDao.addBookmark(groupId, roadAdd);
        } catch (Exception e) {
            logger.error("Failed to add bookmark", e);
        }
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
