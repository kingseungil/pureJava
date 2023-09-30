package com.wifi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WifiData {

    private int id;
    private double distance;
    @JsonProperty("X_SWIFI_MGR_NO")
    private String mgrNo; // 관리번호
    @JsonProperty("X_SWIFI_WRDOFC")
    private String wrdofc; // 자치구
    @JsonProperty("X_SWIFI_MAIN_NM")
    private String mainNm; // 와이파이명
    @JsonProperty("X_SWIFI_ADRES1")
    private String roadAdd; // 도로명주소
    @JsonProperty("X_SWIFI_ADRES2")
    private String roadAddDetail; // 도로명상세주소
    @JsonProperty("X_SWIFI_INSTL_FLOOR")
    private String instlFloor; // 설치위치(층)
    @JsonProperty("X_SWIFI_INSTL_TY")
    private String instlTy; // 설치유형
    @JsonProperty("X_SWIFI_INSTL_MBY")
    private String instlMby; // 설치기관
    @JsonProperty("X_SWIFI_SVC_SE")
    private String svcSe; // 서비스구분
    @JsonProperty("X_SWIFI_CMCWR")
    private String cmcwr; // 망종륲
    @JsonProperty("X_SWIFI_CNSTC_YEAR")
    private int cnstcYear; // 설치년도
    @JsonProperty("X_SWIFI_INOUT_DOOR")
    private String inoutDoor; // 실내외구분
    @JsonProperty("X_SWIFI_REMARS3")
    private String remars3; // wifi접속환경
    // 공공데이터에서 위도,경도가 반대로 표기되어 있음
    @JsonProperty("LNT")
    private double lat; // 위도
    @JsonProperty("LAT")
    private double lnt; // 경도
    @JsonProperty("WORK_DTTM")
    private String workDttm; // 작업일자

    public double calculateDistance(double lat1, double lnt1, double lat2, double lnt2) {
        int R = 6371; // 지구의 반경(km)
        double latDistance = Math.toRadians(lat2 - lat1);
        double lntDistance = Math.toRadians(lnt2 - lnt1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
          + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
          * Math.sin(lntDistance / 2) * Math.sin(lntDistance / 2);

        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }
}
