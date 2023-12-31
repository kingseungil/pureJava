package com.wifi.controller;

import com.wifi.model.BookmarkGroup;
import com.wifi.repository.BookmarkGroupRepository;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "BookmarkGroupServlet", value = "/bookmark-group")
public class BookmarkGroupServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(BookmarkGroupServlet.class);
    private static final BookmarkGroupRepository BOOKMARK_GROUP_REPOSITORY = new BookmarkGroupRepository();

    public static List<BookmarkGroup> getBookmarkGroupList() throws SQLException {
        return BOOKMARK_GROUP_REPOSITORY.getBookmarkGroupList();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<BookmarkGroup> bookmarkGroupList = getBookmarkGroupList();
            request.setAttribute("bookmarkGroupList", bookmarkGroupList);
        } catch (SQLException e) {
            logger.error("Failed to get bookmark group list", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        request.getRequestDispatcher("/WEB-INF/view/bookmark-group.jsp").forward(request, response);
    }

}
