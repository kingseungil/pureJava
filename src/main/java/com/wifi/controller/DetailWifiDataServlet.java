package com.wifi.controller;

import com.wifi.dao.BookmarkGroupDao;
import com.wifi.model.BookmarkGroup;
import com.wifi.model.WifiData;
import com.wifi.service.WifiService;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "detailWifiDataServlet", value = "/detail")
public class DetailWifiDataServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DetailWifiDataServlet.class);

    public static WifiData getDetailWifiData(int id) {
        WifiService wifiService = new WifiService();
        return wifiService.getWifiDataById(id);
    }

    public static List<BookmarkGroup> getBookmarkGroupList() {
        BookmarkGroupDao bookmarkGroupDao = new BookmarkGroupDao();
        return bookmarkGroupDao.getBookmarkGroupList();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || !idStr.matches("\\d+")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            WifiData wifiData = getDetailWifiData(id);
            request.setAttribute("wifiData", wifiData);

            List<BookmarkGroup> bookmarkGroupList = getBookmarkGroupList();
            request.setAttribute("bookmarkGroupList", bookmarkGroupList);

            request.getRequestDispatcher("/WEB-INF/view/detail.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("Failed to get detail wifi data", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


}
