package com.wifi.controller;

import com.wifi.service.WifiService;
import java.io.IOException;
import java.sql.SQLException;

public class OpenAPI {

    public static int fetchData() throws IOException, SQLException {
        WifiService wifiService = new WifiService();
        return wifiService.fetchData();
    }
}
