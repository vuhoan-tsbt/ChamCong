package com.example.chamcong.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StaffResetPasswordTokenResponse {

    private String email;

    private String token;
}
