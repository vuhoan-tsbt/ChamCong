package com.example.chamcong.enumtation;

import lombok.Getter;

@Getter
public enum OTPTypeEnum {

    RESET_PASSWORD("RESET_PASSWORD");
    OTPTypeEnum(String value) {
        this.value = value;
    }

    private String value;
}
