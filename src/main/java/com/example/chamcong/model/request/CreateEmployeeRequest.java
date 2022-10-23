package com.example.chamcong.model.request;

import com.example.chamcong.constant.Messages;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class CreateEmployeeRequest {
    @NotBlank(message = Messages.MES005)
    @Pattern(regexp = "^(.+)@(\\S+)$", message = Messages.MES001)
    @Size(min = 12, max = 100, message = Messages.MES010)
    private String email;

    @NotBlank(message = Messages.MES006)
    @Size(min = 3, max = 100, message = Messages.MES009)
    private String fullName;


    @NotNull(message = Messages.MES015)
    private Integer departmentId;

    @NotNull(message = Messages.MES015)
    private Integer positionId;

    @NotNull(message = Messages.MES016)
    @Pattern(regexp = "[1,2,3]", message = Messages.MES017)
    private String role;
}
