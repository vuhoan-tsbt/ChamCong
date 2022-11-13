package com.example.chamcong.model.request;

import com.example.chamcong.constant.Messages;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ResetPasswordOTPRequest {
    @NotBlank(message = "id không được bỏ trống")
    private Integer id;

    @NotBlank(message = Messages.MES032)
    @Size(min = 6, max = 6, message = Messages.MES033)
    private String otp;

    @NotBlank(message = Messages.MES004)
    @Size(min = 8, max = 100, message = Messages.MES002)
    private String password;
}
