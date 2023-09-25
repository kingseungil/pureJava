package com.wifi.dao;

import com.wifi.model.WifiData;
import com.wifi.util.DaoUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WifiDao {

    private static final Logger logger = LoggerFactory.getLogger(WifiDao.class);

    public void insertWifiDataBatch(List<WifiData> wifiDataList) throws SQLException {
        String query =
          "INSERT INTO wifi (mgrNo, wrdofc, mainNm, roadAdd, roadAddDetail, instlFloor, instlTy, instlMby, svcSe, cmcwr, cnstcYear, inoutDoor, remars3, lat, lnt, workDttm) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        List<Object[]> paramsList = new ArrayList<>();
        for (WifiData wifiData : wifiDataList) {
            Object[] params = {
              wifiData.getMgrNo(),
              wifiData.getWrdofc(),
              wifiData.getMainNm(),
              wifiData.getRoadAdd(),
              wifiData.getRoadAddDetail(),
              wifiData.getInstlFloor(),
              wifiData.getInstlTy(),
              wifiData.getInstlMby(),
              wifiData.getSvcSe(),
              wifiData.getCmcwr(),
              wifiData.getCnstcYear(),
              wifiData.getInoutDoor(),
              wifiData.getRemars3(),
              wifiData.getLat(),
              wifiData.getLnt(),
              wifiData.getWorkDttm()
            };
            paramsList.add(params);
        }
        DaoUtil.executeBatchUpdate(query, paramsList);
    }

    public boolean isDuplicate() {
        String query =
          "SELECT COUNT(*) FROM wifi";
        int count = DaoUtil.executeQuery(query, rs -> rs.getInt(1)).get(0);
        return count > 0;
    }


    public List<WifiData> getAllData() {
        String query = "SELECT * FROM wifi";
        return DaoUtil.executeQuery(query, this::createWifiData);
    }


    public WifiData getWifiDataById(int id) {
        String query = "SELECT * FROM wifi WHERE id = ?";
        return DaoUtil.executeQuery(query, this::createWifiData, id).get(0);
    }

    private WifiData createWifiData(ResultSet rs) throws SQLException {
        WifiData wifiData = new WifiData();
        wifiData.setId(rs.getInt("id"));
        wifiData.setMgrNo(rs.getString("mgrNo"));
        wifiData.setWrdofc(rs.getString("wrdofc"));
        wifiData.setMainNm(rs.getString("mainNm"));
        wifiData.setRoadAdd(rs.getString("roadAdd"));
        wifiData.setRoadAddDetail(rs.getString("roadAddDetail"));
        wifiData.setInstlFloor(rs.getString("instlFloor"));
        wifiData.setInstlTy(rs.getString("instlTy"));
        wifiData.setInstlMby(rs.getString("instlMby"));
        wifiData.setSvcSe(rs.getString("svcSe"));
        wifiData.setCmcwr(rs.getString("cmcwr"));
        wifiData.setCnstcYear(rs.getInt("cnstcYear"));
        wifiData.setInoutDoor(rs.getString("inoutDoor"));
        wifiData.setRemars3(rs.getString("remars3"));
        wifiData.setLat(rs.getDouble("lat"));
        wifiData.setLnt(rs.getDouble("lnt"));
        wifiData.setWorkDttm(rs.getString("workDttm"));
        return wifiData;
    }

}
