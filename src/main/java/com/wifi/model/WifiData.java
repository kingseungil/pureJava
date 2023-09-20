package com.wifi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonProperty("adminNm")
    private String adminNm;
    @JsonProperty("roadAdd")
    private String roadAdd;
    @JsonProperty("detailPlace")
    private String detailPlace;
    @JsonProperty("instfacType")
    private String instfacType;
    @JsonProperty("instplaceNm")
    private String instplaceNm;
    @JsonProperty("standtData")
    private String standtData;
    @JsonProperty("posX")
    private double posX;
    @JsonProperty("posY")
    private double posY;
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
