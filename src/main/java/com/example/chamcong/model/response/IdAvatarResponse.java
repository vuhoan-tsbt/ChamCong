package com.example.chamcong.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class IdAvatarResponse {
    private String message;

    public IdAvatarResponse(String message) {
        this.message = message;
    }
}
