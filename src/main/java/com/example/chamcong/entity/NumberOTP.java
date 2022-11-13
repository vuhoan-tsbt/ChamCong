package com.example.chamcong.entity;

import com.example.chamcong.utils.StaticUtils;

import javax.persistence.Entity;

@Entity
public class NumberOTP extends OTP{
    @Override
    protected String generateOTP() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            stringBuilder.append((char) (StaticUtils.randomInt(9) + 48));
        }
        return stringBuilder.toString();
    }
}
