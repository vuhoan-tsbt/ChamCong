package com.example.chamcong.exception.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DataNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String message;

    public DataNotFoundException(String message) {
        this.message = message;
    }
}
