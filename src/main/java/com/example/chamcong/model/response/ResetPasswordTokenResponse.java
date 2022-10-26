package com.example.chamcong.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResetPasswordTokenResponse {

    private String email;

    private String token;
}
