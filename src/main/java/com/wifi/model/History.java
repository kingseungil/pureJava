package com.wifi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class History {

    private int id;
    private double lat;
    private double lnt;
    private String date;
}
