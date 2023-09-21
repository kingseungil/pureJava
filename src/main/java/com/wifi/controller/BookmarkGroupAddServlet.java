package com.wifi.controller;

import com.wifi.service.BookmarkGroupService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BookmarkGroupAddServlet", value = "/bookmark-group-add")
public class BookmarkGroupAddServlet extends HttpServlet {

    public static void addBookmarkGroup(String name, int rank) throws SQLException {
        BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
        bookmarkGroupService.addBookmarkGroup(name, rank);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/bookmark-group-add.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String rank = request.getParameter("rank");

        try {
            addBookmarkGroup(name, Integer.parseInt(rank));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/bookmark-group");
    }
}
