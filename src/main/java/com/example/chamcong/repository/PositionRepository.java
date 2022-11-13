package com.example.chamcong.repository;

import com.example.chamcong.entity.Position;
import com.example.chamcong.repository.sql.CustomerPositionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PositionRepository extends JpaRepository<Position, Integer>, CustomerPositionRepository {

    @Override
    @Query("select position from Position position where position.id =:id")
    Position getById(Integer id);

    @Query("select position from Position position where position.position = :position")
    Optional<Position> getByName(String position);
}
