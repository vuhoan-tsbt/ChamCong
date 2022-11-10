package com.example.chamcong.model.request;


import com.example.chamcong.constant.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SalaryEmployeeRequest {



    @NotBlank(message = Messages.MES018)
    private String staffCode;

    @NotNull(message = Messages.MES019)
    private Long totalNumberOfWorkingDaysInTheMonth;

    @NotNull(message = Messages.MES019)
    private Long numberOfHoursWorkedInADay;

    @NotNull(message = Messages.MES019)
    private Long totalWorkingHours;
}
