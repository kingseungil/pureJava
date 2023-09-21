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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "nearbyWifiServlet", value = "/nearby-wifi")
public class NearbyWifiServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(NearbyWifiServlet.class);

    public static List<WifiData> getNearbyWifiSpots(PositionRequestDTO positionRequestDTO) throws SQLException {
        WifiService wifiService = new WifiService();
        return wifiService.getNearbyWifiSpots(positionRequestDTO);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PositionRequestDTO positionResponseDTO = new PositionRequestDTO();
            double lat = Double.parseDouble(request.getParameter("lat"));
            double lnt = Double.parseDouble(request.getParameter("lnt"));
            positionResponseDTO.setPosX(lat);
            positionResponseDTO.setPosY(lnt);

            List<WifiData> nearbyWifiSpots = getNearbyWifiSpots(positionResponseDTO);
            request.setAttribute("nearbyWifiSpots", nearbyWifiSpots);
        } catch (SQLException e) {
            logger.error("Failed to get nearby wifi spots", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        request.getRequestDispatcher("/WEB-INF/view/nearby-wifi.jsp").forward(request, response);
    }
}
