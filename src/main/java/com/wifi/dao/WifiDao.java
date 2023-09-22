package com.wifi.dao;

import com.wifi.model.WifiData;
import com.wifi.util.DaoUtil;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WifiDao {

    private static final Logger logger = LoggerFactory.getLogger(WifiDao.class);

    public void insertWifiData(WifiData wifiData) throws SQLException {
        String query =
          "INSERT INTO wifi ( adminNm, roadAdd, detailPlace, instfacType, instplaceNm, standtData, posX, posY, seviceNm) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DaoUtil.executeUpdate(query, wifiData.getAdminNm(), wifiData.getRoadAdd(), wifiData.getDetailPlace(),
          wifiData.getInstfacType(), wifiData.getInstplaceNm(), wifiData.getStandtData(), wifiData.getPosX(),
          wifiData.getPosY(), wifiData.getSeviceNm());
    }

    public boolean isDuplicate(WifiData wifiData) throws SQLException {
        String query =
          "SELECT * FROM wifi WHERE adminNm = ? AND roadAdd = ? AND detailPlace = ? AND instfacType = ? AND instplaceNm = ? AND standtData = ? AND posX = ? AND posY = ? AND seviceNm = ?";
        List<WifiData> result = DaoUtil.executeQuery(query, rs -> {
              WifiData data = new WifiData();
              data.setId(rs.getInt("id"));
              return data;
          }, wifiData.getAdminNm(), wifiData.getRoadAdd(), wifiData.getDetailPlace(), wifiData.getInstfacType(),
          wifiData.getInstplaceNm(), wifiData.getStandtData(), wifiData.getPosX(), wifiData.getPosY(),
          wifiData.getSeviceNm());

        boolean isDuplicate = !result.isEmpty();
        if (isDuplicate) {
            logger.error("Duplicate wifi data: {}", wifiData);
        }
        return isDuplicate;
    }


    public List<WifiData> getAllData() {
        String query = "SELECT * FROM wifi";
        return DaoUtil.executeQuery(query, rs -> {
            WifiData wifiData = new WifiData();
            wifiData.setId(rs.getInt("id"));
            wifiData.setAdminNm(rs.getString("adminNm"));
            wifiData.setRoadAdd(rs.getString("roadAdd"));
            wifiData.setDetailPlace(rs.getString("detailPlace"));
            wifiData.setInstfacType(rs.getString("instfacType"));
            wifiData.setInstplaceNm(rs.getString("instplaceNm"));
            wifiData.setStandtData(rs.getString("standtData"));
            wifiData.setPosX(rs.getDouble("posX"));
            wifiData.setPosY(rs.getDouble("posY"));
            wifiData.setSeviceNm(rs.getString("seviceNm"));
            return wifiData;
        });

    }


    public WifiData getWifiDataById(int id) {
        String query = "SELECT * FROM wifi WHERE id = ?";
        return DaoUtil.executeQuery(query, rs -> {
            WifiData wifiData = new WifiData();
            wifiData.setId(rs.getInt("id"));
            wifiData.setAdminNm(rs.getString("adminNm"));
            wifiData.setRoadAdd(rs.getString("roadAdd"));
            wifiData.setDetailPlace(rs.getString("detailPlace"));
            wifiData.setInstfacType(rs.getString("instfacType"));
            wifiData.setInstplaceNm(rs.getString("instplaceNm"));
            wifiData.setStandtData(rs.getString("standtData"));
            wifiData.setPosX(rs.getDouble("posX"));
            wifiData.setPosY(rs.getDouble("posY"));
            wifiData.setSeviceNm(rs.getString("seviceNm"));
            return wifiData;
        }, id).get(0);

    }

}
