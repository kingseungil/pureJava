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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "historyServlet", value = "/history")
public class HistoryServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(HistoryServlet.class);
    private static final HistoryService historyService = new HistoryService();

    public static List<HistoryResponseDTO> getHistory() throws SQLException {
        return historyService.getHistory();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<HistoryResponseDTO> historyList = getHistory();
            request.setAttribute("historyList", historyList);
        } catch (SQLException e) {
            logger.error("Failed to get history list", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        request.getRequestDispatcher("/WEB-INF/view/history.jsp").forward(request, response);
    }
}
