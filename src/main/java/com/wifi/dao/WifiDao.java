package com.wifi.dao;

import com.wifi.model.WifiData;
import com.wifi.util.SQLiteConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

            int executed = preparedStatement.executeUpdate();
            if (executed == 1) {
                System.out.println("데이터 저장 성공");
            } else {
                System.out.println("데이터 저장 실패");
            }
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
          "SELECT * FROM wifi WHERE posX = " + wifiData.getPosX() + " AND posY = " + wifiData.getPosY();

        try {
            connection = SQLiteConnection.SQLiteUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
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
}
