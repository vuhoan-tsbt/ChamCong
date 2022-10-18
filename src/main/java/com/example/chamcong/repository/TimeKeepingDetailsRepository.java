package com.example.chamcong.repository;

import com.example.chamcong.entity.TimeKeeping;
import com.example.chamcong.entity.TimeKeepingDetails;
import com.example.chamcong.repository.sql.CustomerTimeKeepingDetailsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeKeepingDetailsRepository extends JpaRepository<TimeKeepingDetails,Integer>, CustomerTimeKeepingDetailsRepository {

    @Query("select timekeepingdeatails from TimeKeepingDetails  timekeepingdeatails where timekeepingdeatails.timeKeeping = :timeKeeping")
    TimeKeepingDetails getById(TimeKeeping timeKeeping);
}
