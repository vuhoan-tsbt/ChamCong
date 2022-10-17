package com.example.chamcong.repository;

import com.example.chamcong.entity.TimeKeeping;
import com.example.chamcong.entity.TimeKeepingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeKeepingDetailsRepository extends JpaRepository<TimeKeepingDetails,Integer> {
    @Query("select timekeepingdeatails from TimeKeepingDetails  timekeepingdeatails where timekeepingdeatails.timeKeeping = :timeKeeping")
    TimeKeepingDetails getById(TimeKeeping timeKeeping);
}
