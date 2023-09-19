package com.wifi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class WifiData {

    @Getter
    private double distance;
    @Getter
    @JsonProperty("adminNm")
    private String adminNm;
    @Getter
    @JsonProperty("roadAdd")
    private String roadAdd;
    @Getter
    @JsonProperty("detailPlace")
    private String detailPlace;
    @Getter
    @JsonProperty("instfacType")
    private String instfacType;
    @Getter
    @JsonProperty("instplaceNm")
    private String instplaceNm;
    @Getter
    @JsonProperty("standtData")
    private String standtData;
    @Getter
    @JsonProperty("posX")
    private double posX;
    @Getter
    @JsonProperty("posY")
    private double posY;
    @Getter
    @JsonProperty("seviceNm")
    private String seviceNm;

    @JsonIgnore
    @JsonProperty("adminTel")
    private String adminTel;
    @JsonIgnore
    @JsonProperty("dataSid")
    private String dataSid;
    @JsonIgnore
    @JsonProperty("instYm")
    private String instYm;
    @JsonIgnore
    @JsonProperty("instsidoNm")
    private String instsidoNm;
    @JsonIgnore
    @JsonProperty("instsigunguNm")
    private String instsigunguNm;
    @JsonIgnore
    @JsonProperty("jibunAdd")
    private String jibunAdd;
    @JsonIgnore
    @JsonProperty("wifiSsid")
    private String wifiSsid;
}
