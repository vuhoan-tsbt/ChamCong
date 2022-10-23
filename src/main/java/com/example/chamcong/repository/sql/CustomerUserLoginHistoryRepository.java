package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.UserLoginHistory;

import java.util.List;

public interface CustomerUserLoginHistoryRepository {
    List<UserLoginHistory> finAllUserLoginHistory(Integer id);
}
