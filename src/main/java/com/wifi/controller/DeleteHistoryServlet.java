package com.wifi.controller;

import com.wifi.dao.HistoryDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "deleteHistoryServlet", value = "/history-delete")
public class DeleteHistoryServlet extends HttpServlet {

    public static void deleteHistory(int id) {
        HistoryDao historyDao = new HistoryDao();
        historyDao.deleteHistory(id);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);

        deleteHistory(id);
        response.sendRedirect("/history");
    }

}
