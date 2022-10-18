package com.example.chamcong.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SalaryEmployeeRequest {

    private String staffCode;

    private Long salaryForOneHourWork;

    private Long totalWorkingHouse;
}
