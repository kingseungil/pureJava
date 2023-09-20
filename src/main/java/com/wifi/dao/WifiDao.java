package com.wifi.dao;

import com.wifi.model.WifiData;
import com.wifi.util.SQLiteConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WifiDao {

    public void insertWifiData(WifiData wifiData) throws SQLException {
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            SQLiteConnection.SQLiteUtil.close(connection);
        }
    }

    public boolean isDuplicate(WifiData wifiData) throws SQLException {
        Connection connection = null;
        boolean isDuplicate = false;

        // 데이터가 1개 이상있다면 중복
        String query =
          "SELECT * FROM wifi WHERE adminNm = ? AND roadAdd = ? AND detailPlace = ? AND instfacType = ? AND instplaceNm = ? AND standtData = ? AND posX = ? AND posY = ? AND seviceNm = ?";

        try {
            connection = SQLiteConnection.SQLiteUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, wifiData.getAdminNm());
            ps.setString(2, wifiData.getRoadAdd());
            ps.setString(3, wifiData.getDetailPlace());
            ps.setString(4, wifiData.getInstfacType());
            ps.setString(5, wifiData.getInstplaceNm());
            ps.setString(6, wifiData.getStandtData());
            ps.setDouble(7, wifiData.getPosX());
            ps.setDouble(8, wifiData.getPosY());
            ps.setString(9, wifiData.getSeviceNm());

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                isDuplicate = true;
                System.out.println("WifiDao.isDuplicate: 중복된 데이터가 있습니다.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        } finally {
            SQLiteConnection.SQLiteUtil.close(connection);

        }
        return isDuplicate;
    }

    public List<WifiData> getAllData() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<WifiData> wifiDataList = new ArrayList<>();
        String query = "SELECT * FROM wifi";

        try {
            connection = SQLiteConnection.SQLiteUtil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

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
            e.printStackTrace();
        } finally {
            SQLiteConnection.SQLiteUtil.close(connection);
        }

        return wifiDataList;

    }


    public WifiData getWifiDataById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        WifiData wifiData = new WifiData();
        String query = "SELECT * FROM wifi WHERE id = ?";

        try {
            connection = SQLiteConnection.SQLiteUtil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // resultSet 없다면
            if (resultSet == null) {
                return wifiData;
            }

            while (resultSet.next()) {
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
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            SQLiteConnection.SQLiteUtil.close(connection);
        }

        return wifiData;
    }
}
