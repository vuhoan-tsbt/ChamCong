package com.example.chamcong.repository;

import com.example.chamcong.entity.TimeKeepingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeKeepingDetailsRepository extends JpaRepository<TimeKeepingDetails,Integer> {
}
