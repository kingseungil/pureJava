package com.wifi.controller;

import com.wifi.service.WifiService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "fetchWifiDataServlet", value = "/load-wifi")
public class FetchWifiDataServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(FetchWifiDataServlet.class);
    private static final WifiService wifiService = new WifiService();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int dataCount = wifiService.fetchData();
            if (dataCount == 0) {
                request.setAttribute("message", "이미 데이터가 존재합니다.");
            } else {
                request.setAttribute("message", dataCount + "개의 WIFI 정보를 정상적으로 저장하였습니다.");
            }
        } catch (Exception e) {
            logger.error("Failed to fetch wifi data", e);
            request.setAttribute("message", "데이터 가져오기 실패");
        }

        request.getRequestDispatcher("/WEB-INF/view/load-wifi.jsp").forward(request, response);
    }
}
