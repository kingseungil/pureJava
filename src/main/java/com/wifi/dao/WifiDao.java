package com.wifi.dao;

import com.wifi.model.WifiData;
import com.wifi.util.SQLiteConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WifiDao {

    private static final Logger logger = LoggerFactory.getLogger(WifiDao.class);

    public void insertWifiData(WifiData wifiData) throws SQLException {
        String query =
          "INSERT INTO wifi ( adminNm, roadAdd, detailPlace, instfacType, instplaceNm, standtData, posX, posY, seviceNm) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Failed to insert wifi data", e);
        }
    }

    public boolean isDuplicate(WifiData wifiData) throws SQLException {
        boolean isDuplicate = false;

        // 데이터가 1개 이상있다면 중복
        String query =
          "SELECT * FROM wifi WHERE adminNm = ? AND roadAdd = ? AND detailPlace = ? AND instfacType = ? AND instplaceNm = ? AND standtData = ? AND posX = ? AND posY = ? AND seviceNm = ?";

        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, wifiData.getAdminNm());
            preparedStatement.setString(2, wifiData.getRoadAdd());
            preparedStatement.setString(3, wifiData.getDetailPlace());
            preparedStatement.setString(4, wifiData.getInstfacType());
            preparedStatement.setString(5, wifiData.getInstplaceNm());
            preparedStatement.setString(6, wifiData.getStandtData());
            preparedStatement.setDouble(7, wifiData.getPosX());
            preparedStatement.setDouble(8, wifiData.getPosY());
            preparedStatement.setString(9, wifiData.getSeviceNm());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    isDuplicate = true;
                    logger.error("Duplicate WiFi data");
                }

            } catch (SQLException e) {
                logger.error("Failed to check duplicate WiFi data", e);
            }

        } catch (ClassNotFoundException e) {
            logger.error("Database driver not found", e);
        }

        return isDuplicate;
    }


    public List<WifiData> getAllData() {
        List<WifiData> wifiDataList = new ArrayList<>();
        String query = "SELECT * FROM wifi";

        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          ResultSet resultSet = preparedStatement.executeQuery()) {
            // resultSet 없다면
            if (resultSet == null) {
                return wifiDataList;
            }

            while (resultSet.next()) {
                WifiData wifiData = new WifiData();
                wifiData.setId(resultSet.getInt("id"));
                wifiData.setAdminNm(resultSet.getString("adminNm"));
                wifiData.setRoadAdd(resultSet.getString("roadAdd"));
                wifiData.setDetailPlace(resultSet.getString("detailPlace"));
                wifiData.setInstfacType(resultSet.getString("instfacType"));
                wifiData.setInstplaceNm(resultSet.getString("instplaceNm"));
                wifiData.setStandtData(resultSet.getString("standtData"));
                wifiData.setPosX(resultSet.getDouble("posX"));
                wifiData.setPosY(resultSet.getDouble("posY"));
                wifiData.setSeviceNm(resultSet.getString("seviceNm"));

                wifiDataList.add(wifiData);
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Failed to get all wifi data", e);
        }
        return wifiDataList;

    }


    public WifiData getWifiDataById(int id) {
        WifiData wifiData = new WifiData();
        String query = "SELECT * FROM wifi WHERE id = ?";

        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // resultSet 없다면
                if (!resultSet.next()) {
                    return wifiData;
                }

                do {
                    wifiData.setId(resultSet.getInt("id"));
                    wifiData.setAdminNm(resultSet.getString("adminNm"));
                    wifiData.setRoadAdd(resultSet.getString("roadAdd"));
                    wifiData.setDetailPlace(resultSet.getString("detailPlace"));
                    wifiData.setInstfacType(resultSet.getString("instfacType"));
                    wifiData.setInstplaceNm(resultSet.getString("instplaceNm"));
                    wifiData.setStandtData(resultSet.getString("standtData"));
                    wifiData.setPosX(resultSet.getDouble("posX"));
                    wifiData.setPosY(resultSet.getDouble("posY"));
                    wifiData.setSeviceNm(resultSet.getString("seviceNm"));

                } while (resultSet.next());
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Failed to get WiFi data by ID", e);
        }

        return wifiData;
    }

}
