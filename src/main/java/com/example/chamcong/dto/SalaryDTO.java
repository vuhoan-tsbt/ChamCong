package com.example.chamcong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDTO {

    private String months;
    private Long totalWorkingHoursInTheMonth;
    private Long salary;
}
