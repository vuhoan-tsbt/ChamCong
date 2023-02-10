package com.example.chamcong.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class KeepingStaffDayNowDTO {

    private String fullName;
    private String staffCode;
    private String entryTime;
    private String timeout;
    private Double workingTime;
}
