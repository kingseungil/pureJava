package com.wifi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Bookmark {

    private int id;
    private int bookmark_group_id;
    private String roadArr;
    private String date;
}
