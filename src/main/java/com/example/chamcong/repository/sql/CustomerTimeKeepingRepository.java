package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.TimeKeeping;

import java.util.List;

public interface CustomerTimeKeepingRepository {

    List<TimeKeeping> findByIdUser(Integer id);
}
