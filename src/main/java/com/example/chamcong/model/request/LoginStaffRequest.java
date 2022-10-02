package com.example.chamcong.model.request;

import com.example.chamcong.constant.Messages;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class LoginStaffRequest {
    @NotBlank(message = Messages.MES013)
    private String staffCode;

    @NotBlank(message = Messages.MES004)
    @Size(min = 8, max = 256, message = Messages.MES002)
    private String password;
}
