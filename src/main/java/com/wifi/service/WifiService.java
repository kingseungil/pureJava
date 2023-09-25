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
    private final WifiDao wifiDao = new WifiDao();
    private final HistoryService historyService = new HistoryService();

    /**
     * 외부 API에서 데이터를 가져와서 sqlite db에 저장
     *
     * @return 저장한 데이터의 개수
     */
    public int fetchData() {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new XmlMapper();

        int totalCount = 0; // 총 저장된 데이터 수
        int pageNo = 1; // 현재 페이지 번호
        int pageSize = 1000; // 한 페이지당 아이템 수

        while (true) {
            int startIndex = (pageNo - 1) * pageSize + 1;
            int endIndex = pageNo * pageSize;

            HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
              .scheme("http")
              .host("openapi.seoul.go.kr")
              .port(8088)
              .addPathSegment("634b517743666c793439756e58437a")
              .addPathSegment("xml")
              .addPathSegment("TbPublicWifiInfo")
              .addPathSegment(String.valueOf(startIndex)) // 시작 인덱스
              .addPathSegment(String.valueOf(endIndex)); // 종료 인덱스

            Request request = new Request.Builder().url(urlBuilder.build()).build();

            try (Response response = client.newCall(request).execute()) {

                if (!response.isSuccessful()) {
                    logger.error(String.format("Error: %s", response));
                    break;
                }

                ResponseBody body = response.body();

                if (body == null) {
                    break;
                }

                String xmlData = body.string();
                // xml을 json으로 변환
                JsonNode jsonNode = objectMapper.readTree(xmlData);
                // json에서 데이터 리스트 가져오기
                JsonNode dataList = jsonNode.get("row");
                // 데이터가 없으면 종료
                if (dataList == null || !dataList.elements().hasNext()) {
                    break;
                }
                // 데이터 리스트를 순회하면서 db에 저장
                for (JsonNode item : dataList) {
                    WifiData wifiData = objectMapper.treeToValue(item, WifiData.class);

                    if (!wifiDao.isDuplicate(wifiData)) {
                        wifiDao.insertWifiData(wifiData);
                        totalCount++;
                    } else {
                        return totalCount;
                    }
                }
            } catch (IOException | SQLException e) {
                logger.error("Failed to fetch data ", e);
                return totalCount;
            }
            pageNo++;
        }
        return totalCount;
    }


    /**
     * 모든 데이터 가져오기 + 가져오면서 history에 저장
     */
    public List<WifiData> getNearbyWifiSpots(PositionRequestDTO positionRequestDTO) {
        List<WifiData> allSpots = wifiDao.getAllData();

        for (WifiData spot : allSpots) {
            double distance = CalculateDistance.calculateDistance(positionRequestDTO.getPosX(),
              positionRequestDTO.getPosY(), spot.getLnt(), spot.getLat());
            String formattedDistance = String.format("%.4f", distance);
            spot.setDistance(Double.parseDouble(formattedDistance));
        }

        allSpots.sort(Comparator.comparingDouble(WifiData::getDistance));

        // history에 저장
        historyService.insertHistory(positionRequestDTO);

        return allSpots.subList(0, Math.min(20, allSpots.size()));
    }

    /**
     * 데이터 하나 가져오기
     */
    public WifiData getWifiDataById(int id) {
        WifiData existData = wifiDao.getWifiDataById(id);

        // 최근에 조회한 history에서 사용자의 위도,경도 가져오기
        PositionResponseDTO latestPosition = historyService.getLatestHistory();

        // 사용자의 위도,경도와 해당 데이터의 위도,경도로 거리 계산
        double distance = CalculateDistance.calculateDistance(latestPosition.getPosX(),
          latestPosition.getPosY(), existData.getLnt(), existData.getLat());

        String formattedDistance = String.format("%.4f", distance);
        existData.setDistance(Double.parseDouble(formattedDistance));

        return existData;
    }
}
