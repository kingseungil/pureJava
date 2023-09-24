package com.wifi.controller;

import com.wifi.dto.response.BookmarkResponseDTO;
import com.wifi.service.BookmarkService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "deleteBookmarkServlet", value = "/bookmark-delete")
public class DeleteBookmarkServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DeleteBookmarkServlet.class);
    private static final BookmarkService bookmarkService = new BookmarkService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = validateAndGetId(request, response);
            BookmarkResponseDTO bookmark = bookmarkService.getBookmark(id);
            request.setAttribute("bookmark", bookmark);
            request.getRequestDispatcher("/WEB-INF/view/bookmark-delete.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("Failed to delete bookmark", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = validateAndGetId(request, response);
            bookmarkService.deleteBookmark(id);
            response.sendRedirect("/bookmark-list");
        } catch (Exception e) {
            logger.error("Failed to delete bookmark", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private int validateAndGetId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || !idStr.matches("\\d+")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return -1;
        }
        return Integer.parseInt(idStr);
    }
}
