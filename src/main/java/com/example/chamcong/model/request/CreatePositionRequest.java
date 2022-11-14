package com.example.chamcong.model.request;

import com.example.chamcong.constant.Messages;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class CreatePositionRequest {

    @NotNull(message = Messages.MES025)
    private Long allowance;

    @NotBlank(message = Messages.MES026)
    private String position;

    @NotNull(message = Messages.MES027)
    private Long salary;

    @NotNull(message = Messages.MES028)
    private Integer wage;
}
