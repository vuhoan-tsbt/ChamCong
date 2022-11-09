package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.Position;
import com.example.chamcong.model.request.PositionRequest;

import java.util.List;

public interface CustomerPositionRepository {

    List<Position> findByAllPosition(PositionRequest input);

    Integer getAllPosition(PositionRequest input);
}
