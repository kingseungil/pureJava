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

@WebServlet(name = "BookmarkGroupAddServlet", value = "/bookmark-group-add")
public class AddBookmarkGroupServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(AddBookmarkGroupServlet.class);

    public static void addBookmarkGroup(String name, int rank) throws SQLException {
        BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
        bookmarkGroupService.addBookmarkGroup(name, rank);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/bookmark-group-add.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String rank = request.getParameter("rank");

        if (name == null || name.isEmpty() || rank == null || !rank.matches("\\d+")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            addBookmarkGroup(name, Integer.parseInt(rank));
            response.sendRedirect("/bookmark-group");
        } catch (SQLException e) {
            logger.error("Failed to add bookmark group", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
