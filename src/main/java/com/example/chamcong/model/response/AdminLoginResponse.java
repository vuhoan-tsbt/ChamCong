package com.example.chamcong.model.response;

import com.example.chamcong.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminLoginResponse {
    
    private String token;

    private String refreshToken;
}