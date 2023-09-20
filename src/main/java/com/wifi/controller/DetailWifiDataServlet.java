package com.wifi.controller;

import com.wifi.model.WifiData;
import com.wifi.service.WifiService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "detailWifiDataServlet", value = "/detail")
public class DetailWifiDataServlet extends HttpServlet {

    public static WifiData getDetailWifiData(int id) {
        WifiService wifiService = new WifiService();
        return wifiService.getWifiDataById(id);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WifiData wifiData = getDetailWifiData(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("wifiData", wifiData);

        request.getRequestDispatcher("/WEB-INF/view/detail.jsp").forward(request, response);
    }

}
