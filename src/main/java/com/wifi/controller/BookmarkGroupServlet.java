package com.wifi.controller;

import com.wifi.dao.BookmarkGroupDao;
import com.wifi.model.BookmarkGroup;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BookmarkGroupServlet", value = "/bookmark-group")
public class BookmarkGroupServlet extends HttpServlet {

    public static List<BookmarkGroup> getBookmarkGroupList() throws SQLException {
        BookmarkGroupDao bookmarkGroupDao = new BookmarkGroupDao();
        return bookmarkGroupDao.getBookmarkGroupList();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<BookmarkGroup> bookmarkGroupList = getBookmarkGroupList();
            request.setAttribute("bookmarkGroupList", bookmarkGroupList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/view/bookmark-group.jsp").forward(request, response);
    }

}
