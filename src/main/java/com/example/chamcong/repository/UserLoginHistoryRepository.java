package com.example.chamcong.repository;

import com.example.chamcong.entity.UserLoginHistory;
import com.example.chamcong.repository.sql.CustomerUserLoginHistoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface UserLoginHistoryRepository extends JpaRepository<UserLoginHistory,Integer>, CustomerUserLoginHistoryRepository {

}
