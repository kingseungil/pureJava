package com.example.purejava;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import openApi.OpenAPI;

@WebServlet(name = "fetchDataServlet", value = "/load-wifi")
public class FetchDataServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int dataCount = OpenAPI.fetchData();
            request.setAttribute("message", dataCount + "개의 WIFI 정보를 정상적으로 저장하였습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "데이터 가져오기 실패");
        }

        request.getRequestDispatcher("/WEB-INF/view/load-wifi.jsp").forward(request, response);

    }

    public void destroy() {
    }

}
