package com.example.chamcong.model.request;

import com.example.chamcong.constant.Messages;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class StaffUpdateInformationRequest {


    @NotBlank(message = Messages.MES006)
    @Size(min = 3, max = 100, message = Messages.MES009)
    private String fullName;

    @NotBlank(message = Messages.MES007)
    @Pattern(regexp = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = Messages.MES008)
    private String dateOfBirth;

    @NotBlank(message = Messages.MES011)
    @Size(min = 5, max = 30, message = Messages.MES012)
    private String address;

    @NotBlank(message = Messages.MES004)
    @Size(min =8, max = 100, message = Messages.MES002)
    private String password;

}