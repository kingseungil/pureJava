package com.wifi.controller;

import com.wifi.dto.response.BookmarkResponseDTO;
import com.wifi.service.BookmarkService;
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

@WebServlet(name = "bookmarkServlet", value = "/bookmark-list")
public class BookmarkServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(BookmarkServlet.class);
    private static final BookmarkService bookmarkService = new BookmarkService();

    public static List<BookmarkResponseDTO> getBookmarkList() throws SQLException {
        return bookmarkService.getBookmarkList();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<BookmarkResponseDTO> bookmarkList = getBookmarkList();
            request.setAttribute("bookmarkList", bookmarkList);
            request.getRequestDispatcher("/WEB-INF/view/bookmark-list.jsp").forward(request, response);
        } catch (SQLException e) {
            logger.error("Failed to get bookmark list", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
