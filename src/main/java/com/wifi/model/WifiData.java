package com.wifi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class WifiData {

    @Getter
    @Setter
    private double distance;
    @Getter
    @Setter
    @JsonProperty("adminNm")
    private String adminNm;
    @Getter
    @Setter
    @JsonProperty("roadAdd")
    private String roadAdd;
    @Getter
    @Setter
    @JsonProperty("detailPlace")
    private String detailPlace;
    @Getter
    @Setter
    @JsonProperty("instfacType")
    private String instfacType;
    @Getter
    @Setter
    @JsonProperty("instplaceNm")
    private String instplaceNm;
    @Getter
    @Setter
    @JsonProperty("standtData")
    private String standtData;
    @Getter
    @Setter
    @JsonProperty("posX")
    private double posX;
    @Getter
    @Setter
    @JsonProperty("posY")
    private double posY;
    @Getter
    @Setter
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
