package com.wifi.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.wifi.dao.WifiDao;
import com.wifi.dto.request.PositionRequestDTO;
import com.wifi.dto.response.PositionResponseDTO;
import com.wifi.model.WifiData;
import com.wifi.util.CalculateDistance;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WifiService {

    private static final Logger logger = LoggerFactory.getLogger(WifiService.class);

    /**
     * 외부 API에서 데이터를 가져와서 sqlite db에 저장
     *
     * @return 저장한 데이터의 개수
     */
    public int fetchData() {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
          .scheme("http")
          .host("openapi.jeonju.go.kr")
          .addPathSegment("rest")
          .addPathSegment("wifizone")
          .addPathSegment("getWifiList")
          .addQueryParameter("serviceKey",
            "+KWJ7Ig2kpzGyjXyXNi4paYsEhrlcUaSB2gEW0slUsweLN/ygODs7k5ZqA6NLHp3bwhAfHV7KcbZr1k9zSo6ng==") // 서비스키
          .addQueryParameter("pageNo", "1") // 시작페이지
          .addQueryParameter("numOfRows", "1000000"); // 페이지크기

        Request request = new Request.Builder().url(urlBuilder.build()).build();
        List<WifiData> wifiDataList = new ArrayList<>();

        try (Response response = client.newCall(request).execute()) {

            if (response.isSuccessful()) {

                ResponseBody body = response.body();

                if (body != null) {
                    String xmlData = body.string();

                    // XML to JSON
                    ObjectMapper objectMapper = new XmlMapper();
                    JsonNode jsonNode = objectMapper.readTree(xmlData);

                    // JSON to POJO
                    JsonNode dataList = jsonNode.get("body").get("data").get("list");
                    for (JsonNode item : dataList) {
                        WifiData wifiData = objectMapper.treeToValue(item, WifiData.class);
                        wifiDataList.add(wifiData);
                    }
                    WifiDao wifiDao = new WifiDao();

                    for (WifiData wifiData : wifiDataList) {
                        if (!wifiDao.isDuplicate(wifiData)) {
                            wifiDao.insertWifiData(wifiData);
                        } else {
                            break;
                        }
                    }
                }
            } else {
                logger.error(String.format("Error: %s", response));
            }

        } catch (IOException | SQLException e) {
            logger.error("Failed to fetch data ", e);
        }

        return wifiDataList.size();
    }


    /**
     * 모든 데이터 가져오기 + 가져오면서 history에 저장
     */
    public List<WifiData> getNearbyWifiSpots(PositionRequestDTO positionRequestDTO) {
        WifiDao wifiDao = new WifiDao();
        List<WifiData> allSpots = wifiDao.getAllData();

        for (WifiData spot : allSpots) {
            double distance = CalculateDistance.calculateDistance(positionRequestDTO.getPosX(),
              positionRequestDTO.getPosY(), spot.getPosY(), spot.getPosX());
            String formattedDistance = String.format("%.4f", distance);
            spot.setDistance(Double.parseDouble(formattedDistance));
        }

        allSpots.sort(Comparator.comparingDouble(WifiData::getDistance));

        // history에 저장
        HistoryService historyService = new HistoryService();
        historyService.insertHistory(positionRequestDTO);

        return allSpots.subList(0, Math.min(20, allSpots.size()));
    }

    /**
     * 데이터 하나 가져오기
     */
    public WifiData getWifiDataById(int id) {
        WifiDao wifiDao = new WifiDao();
        WifiData existData = wifiDao.getWifiDataById(id);

        // 최근에 조회한 history에서 사용자의 위도,경도 가져오기
        HistoryService historyService = new HistoryService();
        PositionResponseDTO latestPosition = historyService.getLatestHistory();

        // 사용자의 위도,경도와 해당 데이터의 위도,경도로 거리 계산
        double distance = CalculateDistance.calculateDistance(latestPosition.getPosX(),
          latestPosition.getPosY(), existData.getPosY(), existData.getPosX());

        String formattedDistance = String.format("%.4f", distance);
        existData.setDistance(Double.parseDouble(formattedDistance));

        return existData;
    }
}
