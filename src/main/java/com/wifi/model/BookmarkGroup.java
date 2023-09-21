package com.wifi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookmarkGroup {

    private int id;
    private String name;
    private int rank;
    private String created_date;
    private String updated_date;
}
