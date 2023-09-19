package openApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import util.SQLiteConnection;


public class OpenAPI {


    public static void main(String[] args) throws IOException, SQLException {
        OkHttpClient client = new OkHttpClient();
        StringBuilder urlBuilder = new StringBuilder(
          "http://openapi.jeonju.go.kr/rest/wifizone/getWifiList"); /*URL*/
        urlBuilder.append("?").append(URLEncoder.encode("serviceKey", "UTF-8")).append("=")
                  .append(
                    "%2BKWJ7Ig2kpzGyjXyXNi4paYsEhrlcUaSB2gEW0slUsweLN%2FygODs7k5ZqA6NLHp3bwhAfHV7KcbZr1k9zSo6ng%3D%3D"
                  ); /*Service Key*/
//        urlBuilder.append("&").append(URLEncoder.encode("authApiKey", "UTF-8")).append("=")
//                  .append(
//                    "%2BKWJ7Ig2kpzGyjXyXNi4paYsEhrlcUaSB2gEW0slUsweLN%2FygODs7k5ZqA6NLHp3bwhAfHV7KcbZr1k9zSo6ng%3D%3D"
//                  ); /*인증키*/
        urlBuilder.append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=")
                  .append(URLEncoder.encode("1", "UTF-8")); /*시작페이지*/
        urlBuilder.append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=")
                  .append(URLEncoder.encode("5", "UTF-8")); /*페이지크기*/

        Request request = new Request.Builder().url(urlBuilder.toString()).build();

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
                    List<WifiData> wifiDataList = new ArrayList<>();
                    for (JsonNode item : dataList) {
                        WifiData wifiData = objectMapper.treeToValue(item, WifiData.class);
                        wifiDataList.add(wifiData);
                    }

                    // sqlite db에 저장
                    for (WifiData wifiData : wifiDataList) {
                        if (isDuplicateData(SQLiteConnection.SQLiteUtil.getConnection(), wifiData)) {
                            System.out.println("중복된 데이터입니다.");
                            return;
                        }
                        insertDataIntoSQLite(wifiData);
                    }
                }
            } else {
                System.out.println("Error: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // sqlite db에 저장하는 메소드
    public static void insertDataIntoSQLite(WifiData wifiData) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query =
          "INSERT INTO wifi ( adminNm, roadAdd, detailPlace, instfacType, instplaceNm, standtData, posX, posY, seviceNm) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = SQLiteConnection.SQLiteUtil.getConnection();

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, wifiData.getAdminNm());
            preparedStatement.setString(2, wifiData.getRoadAdd());
            preparedStatement.setString(3, wifiData.getDetailPlace());
            preparedStatement.setString(4, wifiData.getInstfacType());
            preparedStatement.setString(5, wifiData.getInstplaceNm());
            preparedStatement.setString(6, wifiData.getStandtData());
            preparedStatement.setDouble(7, wifiData.getPosX());
            preparedStatement.setDouble(8, wifiData.getPosY());
            preparedStatement.setString(9, wifiData.getSeviceNm());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLiteConnection.SQLiteUtil.close(connection);
        }
    }

    private static boolean isDuplicateData(Connection connection, WifiData wifiData) {
        // 데이터가 1개 이상있다면 중복
        String query = "SELECT * FROM wifi WHERE posX = " + wifiData.getPosX() + " AND posY = "
          + wifiData.getPosY();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
