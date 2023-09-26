package com.wifi.controller;

import com.wifi.service.BookmarkGroupService;
import com.wifi.util.RequestUtil;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "deleteBookmarkGroupServlet", value = "/bookmark-group-delete")
public class DeleteBookmarkGroupServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DeleteBookmarkGroupServlet.class);
    private static final BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();

    public static void deleteBookmarkGroup(int id) throws SQLException {
        bookmarkGroupService.deleteBookmarkGroup(id);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int id = RequestUtil.validateId(request, response);
            deleteBookmarkGroup(id);
            response.sendRedirect("/bookmark-group");
        } catch (Exception e) {
            logger.error("Failed to delete bookmark", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
