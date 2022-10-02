package com.example.chamcong.enumtation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccStatusEnum {
    CREATED("CREATED"),
    ACTIVATED("ACTIVATED"),
    BANNED("BANNED");

    private String value;
}
