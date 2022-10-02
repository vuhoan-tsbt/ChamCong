package com.example.chamcong.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminLoginResponse {

    private String token;

    private String refreshToken;
}
