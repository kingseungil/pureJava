package com.wifi.controller;

import com.wifi.model.WifiData;
import com.wifi.service.WifiService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "fetchNearbyWifiServlet", value = "/nearby-wifi")
public class FetchNearbyWifiServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            WifiService wifiService = new WifiService();
            double lat = Double.parseDouble(request.getParameter("lat"));
            double lnt = Double.parseDouble(request.getParameter("lnt"));

            List<WifiData> nearbyWifiSpots = wifiService.getNearbyWifiSpots(lat, lnt);
            request.setAttribute("nearbyWifiSpots", nearbyWifiSpots);
            request.getRequestDispatcher("/WEB-INF/view/nearby-wifi.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
