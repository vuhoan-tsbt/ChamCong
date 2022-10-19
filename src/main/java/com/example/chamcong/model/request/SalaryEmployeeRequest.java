package com.example.chamcong.model.request;

import com.example.chamcong.constant.Messages;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class SalaryEmployeeRequest {

    @NotBlank(message = Messages.MES018)
    private String staffCode;

    @NotBlank(message = Messages.MES019)
    private Long totalNumberOfWorkingDaysInTheMonth;

    @NotBlank(message = Messages.MES019)
    private Long numberOfHoursWorkedInADay;

    @NotBlank(message = Messages.MES019)
    private Long totalWorkingHours;
}
