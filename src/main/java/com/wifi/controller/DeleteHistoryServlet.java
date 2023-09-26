package com.wifi.controller;

import com.wifi.dao.HistoryDao;
import com.wifi.util.RequestUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "deleteHistoryServlet", value = "/history-delete")
public class DeleteHistoryServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DeleteHistoryServlet.class);
    private static final HistoryDao historyDao = new HistoryDao();

    public static void deleteHistory(int id) {
        historyDao.deleteHistory(id);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = RequestUtil.validateId(request, response);
            deleteHistory(id);
            response.sendRedirect("/history");
        } catch (Exception e) {
            logger.error("Failed to delete history", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
