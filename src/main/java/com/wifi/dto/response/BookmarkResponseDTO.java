package com.wifi.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookmarkResponseDTO {

    private int id;
    private int bookmark_group_id;
    private String group_name;
    private String roadAdd;
    private String date;
}
