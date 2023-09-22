package com.wifi.controller;

import com.wifi.dao.BookmarkGroupDao;
import com.wifi.model.BookmarkGroup;
import com.wifi.service.BookmarkGroupService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "updateBookmarkGroupServlet", value = "/bookmark-group-update")
public class UpdateBookmarkGroupServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(UpdateBookmarkGroupServlet.class);
    private static final BookmarkGroupDao bookmarkGroupDao = new BookmarkGroupDao();
    private static final BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = validateAndGetId(request, response);
            BookmarkGroup bookmarkGroup = bookmarkGroupDao.getBookmarkGroupById(id);
            request.setAttribute("bookmarkGroup", bookmarkGroup);
            request.getRequestDispatcher("/WEB-INF/view/bookmark-group-update.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("Failed to get bookmark group", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            int id = validateAndGetId(request, response);
            String name = request.getParameter("name");
            int rank = Integer.parseInt(request.getParameter("rank"));
            bookmarkGroupService.updateBookmarkGroup(id, name, rank);
            response.sendRedirect("/bookmark-group");
        } catch (Exception e) {
            logger.error("Failed to update bookmark group", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
