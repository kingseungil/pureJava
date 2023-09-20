package com.wifi.controller;

import com.wifi.dto.response.HistoryResponseDTO;
import com.wifi.service.HistoryService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "historyServlet", value = "/history")
public class HistoryServlet extends HttpServlet {

    public static List<HistoryResponseDTO> getHistory() throws SQLException {
        HistoryService historyService = new HistoryService();
        return historyService.getHistory();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<HistoryResponseDTO> historyList = getHistory();
            request.setAttribute("historyList", historyList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/view/history.jsp").forward(request, response);
    }
}
