package com.example.chamcong.model.request;

import com.example.chamcong.constant.Messages;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ForgotPasswordOTPRequest {

    @NotBlank(message = Messages.MES023)
    @Size(min = 10, max = 10, message = Messages.MES024)
    private String phone;
}
