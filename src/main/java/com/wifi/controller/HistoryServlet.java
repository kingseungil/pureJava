package com.wifi.controller;

import com.wifi.model.History;
import com.wifi.service.HistoryService;
import java.io.IOException;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<History> historyList = historyService.getHistory();
            request.setAttribute("historyList", historyList);
            request.getRequestDispatcher("/WEB-INF/view/history.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("Failed to get history", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
