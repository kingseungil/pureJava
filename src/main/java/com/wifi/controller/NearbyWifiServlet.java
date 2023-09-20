package com.wifi.controller;

import com.wifi.dto.request.PositionRequestDTO;
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

@WebServlet(name = "nearbyWifiServlet", value = "/nearby-wifi")
public class NearbyWifiServlet extends HttpServlet {

    public static List<WifiData> getNearbyWifiSpots(PositionRequestDTO positionRequestDTO) throws SQLException {
        WifiService wifiService = new WifiService();
        return wifiService.getNearbyWifiSpots(positionRequestDTO);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PositionRequestDTO positionRequestDTO = new PositionRequestDTO();
            double lat = Double.parseDouble(request.getParameter("lat"));
            double lnt = Double.parseDouble(request.getParameter("lnt"));
            positionRequestDTO.setPosX(lat);
            positionRequestDTO.setPosY(lnt);

            List<WifiData> nearbyWifiSpots = getNearbyWifiSpots(positionRequestDTO);
            request.setAttribute("nearbyWifiSpots", nearbyWifiSpots);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/view/nearby-wifi.jsp").forward(request, response);
    }
}
