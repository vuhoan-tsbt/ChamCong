package com.example.chamcong.model.request;

import com.example.chamcong.constant.Messages;
import com.example.chamcong.enumtation.AccStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class UpdateEmployeeRequest {

    @NotBlank(message = Messages.MES005)
    @Pattern(regexp = "^(.+)@(\\S+)$", message = Messages.MES001)
    @Size(min = 12, max = 100, message = Messages.MES010)
    private String email;

    @NotBlank(message = Messages.MES006)
    @Size(min = 3, max = 100, message = Messages.MES009)
    private String fullName;

    @NotBlank(message = Messages.MES021)
    @Pattern(regexp = "[0,1,2]", message = Messages.MES020)
    private String status;
}
