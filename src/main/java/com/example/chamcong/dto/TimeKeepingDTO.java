package com.example.chamcong.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TimeKeepingDTO {

    private String day;

    private String entryTime;

    private String timeout;
}
