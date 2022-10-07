package com.example.chamcong.model.request;

import com.example.chamcong.constant.Messages;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class StaffResetPasswordTokenRequest {

    @NotBlank(message = Messages.MES005)
    @Pattern(regexp = "^(.+)@(\\S+)$", message = Messages.MES001)
    @Size(min = 12, max = 100, message = Messages.MES010)
    private String email;

    @NotBlank(message = Messages.MES014)
    private String token;

    @NotBlank(message = Messages.MES004)
    @Size(min = 8, max = 256, message = Messages.MES002)
    private String password;
}
