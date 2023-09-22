package com.wifi.controller;

import com.wifi.service.BookmarkGroupService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "deleteBookmarkServlet", value = "/bookmark-delete")
public class DeleteBookmarkServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DeleteBookmarkServlet.class);

    public static void deleteBookmark(int id) throws SQLException {
        BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
        bookmarkGroupService.deleteBookmarkGroup(id);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || !idStr.matches("\\d+")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            deleteBookmark(id);
            response.sendRedirect("/bookmark-group");
        } catch (Exception e) {
            logger.error("Failed to delete bookmark", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}