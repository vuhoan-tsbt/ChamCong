package com.example.chamcong.repository;

import com.example.chamcong.entity.TimeKeeping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeKeepingRepository extends JpaRepository<TimeKeeping, Integer> {
}
