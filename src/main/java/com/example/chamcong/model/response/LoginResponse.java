package com.example.chamcong.model.response;

import com.example.chamcong.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginResponse {

    private String token;

    private String refreshToken;

    private Integer id;
    private String email;
    private String fullName;
    private String role;
}
