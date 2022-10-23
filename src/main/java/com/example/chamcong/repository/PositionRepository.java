package com.example.chamcong.repository;

import com.example.chamcong.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    @Query("select position from Position position where position.id =:id")
    Position getById(Integer id);
}
