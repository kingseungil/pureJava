package com.wifi.controller;

import com.wifi.service.BookmarkService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "addBookmarkServlet", value = "/bookmark-add")
public class AddBookmarkServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(AddBookmarkServlet.class);
    private static final BookmarkService bookmarkService = new BookmarkService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int wifiId = Integer.parseInt(request.getParameter("wifiDataId"));
            int groupId = Integer.parseInt(request.getParameter("groupId"));

            bookmarkService.addBookmark(wifiId, groupId);
            response.sendRedirect("/bookmark-list");
        } catch (Exception e) {
            logger.error("Failed to add bookmark", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
