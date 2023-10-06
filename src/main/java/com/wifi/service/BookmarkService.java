package com.wifi.service;

import com.wifi.dto.response.BookmarkResponseDTO;
import com.wifi.model.WifiData;
import com.wifi.repository.BookmarkRepository;
import com.wifi.repository.WifiRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookmarkService {

    private static final Logger logger = LoggerFactory.getLogger(BookmarkService.class);
    private final BookmarkRepository bookmarkRepository = new BookmarkRepository();
    private final WifiRepository wifiRepository = new WifiRepository();


    public void addBookmark(int wifiId, int groupId) {
        WifiData wifiData = wifiRepository.getWifiDataById(wifiId);
        String roadAdd = wifiData.getRoadAdd();

        try {
            bookmarkRepository.addBookmark(groupId, roadAdd);
        } catch (Exception e) {
            logger.error("Failed to add bookmark", e);
        }
    }

    public void deleteBookmark(int id) {
        bookmarkRepository.deleteBookmark(id);
    }

    public BookmarkResponseDTO getBookmark(int id) {
        return bookmarkRepository.getBookmark(id);
    }

    public List<BookmarkResponseDTO> getBookmarkList() {
        return bookmarkRepository.getBookmarkList();
    }

}
