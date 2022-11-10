package com.example.chamcong.model.response;


import lombok.Data;

import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ListSalaryEmployeeResponse  {



    private String staffCode;

    private String fullName;

    private Long  salary;
}
