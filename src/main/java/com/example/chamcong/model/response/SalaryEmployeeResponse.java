package com.example.chamcong.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SalaryEmployeeResponse {

    private String name;

    private String staffCode;

    private long actualGrossSalary;
}
