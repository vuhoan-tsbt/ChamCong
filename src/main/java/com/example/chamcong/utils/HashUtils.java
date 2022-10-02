package com.example.chamcong.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashUtils {
    public String hash(String content) {
        return new BCryptPasswordEncoder().encode(content);
    }

    public boolean check(String content, String hash) {
        return new BCryptPasswordEncoder().matches(content, hash);
    }
}
