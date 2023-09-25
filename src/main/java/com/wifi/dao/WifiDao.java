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
          "INSERT INTO wifi (mgrNo, wrdofc, mainNm, roadAdd, roadAddDetail, instlFloor, instlTy, instlMby, svcSe, cmcwr, cnstcYear, inoutDoor, remars3, lat, lnt, workDttm) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DaoUtil.executeUpdate(query, wifiData.getMgrNo(), wifiData.getWrdofc(), wifiData.getMainNm(),
          wifiData.getRoadAdd(), wifiData.getRoadAddDetail(), wifiData.getInstlFloor(), wifiData.getInstlTy(),
          wifiData.getInstlMby(), wifiData.getSvcSe(), wifiData.getCmcwr(), wifiData.getCnstcYear(),
          wifiData.getInoutDoor(), wifiData.getRemars3(), wifiData.getLat(), wifiData.getLnt(), wifiData.getWorkDttm());
    }

    public boolean isDuplicate(WifiData wifiData) throws SQLException {
        String query =
          "SELECT * FROM wifi WHERE mgrNo = ?";
        List<WifiData> result = DaoUtil.executeQuery(query, rs -> {
            WifiData data = new WifiData();
            data.setId(rs.getInt("id"));
            data.setMgrNo(rs.getString("mgrNo"));
            return data;
        }, wifiData.getMgrNo());

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
        });

    }


    public WifiData getWifiDataById(int id) {
        String query = "SELECT * FROM wifi WHERE id = ?";
        return DaoUtil.executeQuery(query, rs -> {
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
        }, id).get(0);

    }

}
