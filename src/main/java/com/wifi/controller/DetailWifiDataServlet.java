package com.wifi.controller;

import com.wifi.dao.BookmarkGroupDao;
import com.wifi.model.BookmarkGroup;
import com.wifi.model.WifiData;
import com.wifi.service.WifiService;
import com.wifi.util.RequestUtil;
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
    private static final WifiService wifiService = new WifiService();
    private static final BookmarkGroupDao bookmarkGroupDao = new BookmarkGroupDao();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            int id = RequestUtil.validateId(request, response);
            WifiData wifiData = wifiService.getWifiDataById(id);
            request.setAttribute("wifiData", wifiData);

            List<BookmarkGroup> bookmarkGroupList = bookmarkGroupDao.getBookmarkGroupList();
            request.setAttribute("bookmarkGroupList", bookmarkGroupList);

            request.getRequestDispatcher("/WEB-INF/view/detail.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("Failed to get detail wifi data", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
