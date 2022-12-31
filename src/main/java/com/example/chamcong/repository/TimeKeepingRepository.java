package com.example.chamcong.repository;

import com.example.chamcong.entity.TimeKeeping;
import com.example.chamcong.entity.User;
import com.example.chamcong.repository.sql.CustomerTimeKeepingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeKeepingRepository extends JpaRepository<TimeKeeping, Integer>, CustomerTimeKeepingRepository {

    @Query("select timekeeping from TimeKeeping timekeeping where timekeeping.user =:user")
    List<TimeKeeping> getByUserId(User user);
    @Query("select timekeepingdeatails from TimeKeeping  timekeepingdeatails where timekeepingdeatails.entryTime = :timeKeeping")
    TimeKeeping getById(TimeKeeping timeKeeping);
}
