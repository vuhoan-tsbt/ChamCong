package com.example.chamcong.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TimeKeepingDetailsDTO {

    private String entryTime;
    private String timeout;
    private Double workingTime;
}
