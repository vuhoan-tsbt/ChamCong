package com.example.chamcong.repository;

import com.example.chamcong.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Integer> {
    @Query(nativeQuery = true, value = "select * from otp where target_id = :id order by created_at desc limit 1")
    Optional<OTP> getByTargetId(Integer id);

}
