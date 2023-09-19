package com.wifi.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.wifi.dao.WifiDao;
import com.wifi.model.WifiData;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WifiService {

    public int fetchData() throws IOException, SQLException {
        OkHttpClient client = new OkHttpClient();
        StringBuilder urlBuilder = new StringBuilder(
          "http://openapi.jeonju.go.kr/rest/wifizone/getWifiList"); /*URL*/
        urlBuilder.append("?").append(URLEncoder.encode("serviceKey", "UTF-8")).append("=")
                  .append(
                    "%2BKWJ7Ig2kpzGyjXyXNi4paYsEhrlcUaSB2gEW0slUsweLN%2FygODs7k5ZqA6NLHp3bwhAfHV7KcbZr1k9zSo6ng%3D%3D"
                  ); /*Service Key*/
        urlBuilder.append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=")
                  .append(URLEncoder.encode("1", "UTF-8")); /*시작페이지*/
        urlBuilder.append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=")
                  .append(URLEncoder.encode("5", "UTF-8")); /*페이지크기*/

        Request request = new Request.Builder().url(urlBuilder.toString()).build();
        List<WifiData> wifiDataList = new ArrayList<>();
        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    String xmlData = body.string();
                    // xml을 json으로 변환
                    ObjectMapper objectMapper = new XmlMapper();
                    JsonNode jsonNode = objectMapper.readTree(xmlData);

                    // json에서 원하는 데이터 추출
                    JsonNode dataList = jsonNode.get("body").get("data").get("list");
                    wifiDataList = new ArrayList<>();
                    for (JsonNode item : dataList) {
                        WifiData wifiData = objectMapper.treeToValue(item, WifiData.class);
                        wifiDataList.add(wifiData);
                    }

                    WifiDao wifiDao = new WifiDao();
                    // sqlite db에 저장
                    for (WifiData wifiData : wifiDataList) {
                        if (wifiDao.isDuplicate(wifiData)) {
                            break;
                        }
                        wifiDao.insertWifiData(wifiData);
                    }
                }
            } else {
                System.out.println("Error: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wifiDataList.size();
    }
}