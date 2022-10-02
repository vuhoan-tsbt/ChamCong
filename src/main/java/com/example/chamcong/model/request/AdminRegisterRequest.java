package com.example.chamcong.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.chamcong.constant.Messages;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminRegisterRequest {
    @NotBlank(message = Messages.MES005)
    @Pattern(regexp = "^(.+)@(\\S+)$", message = Messages.MES001)
    @Size(min = 12, max = 100, message = Messages.MES010)
    private String email;

    @NotBlank(message = Messages.MES004)
    @Size(min = 8, max = 100, message = Messages.MES003)
    private String password;
}
