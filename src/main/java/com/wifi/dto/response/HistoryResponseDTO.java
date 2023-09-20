package com.wifi.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HistoryResponseDTO {

    private int id;
    private double posX;
    private double posY;
    private String date;
}
