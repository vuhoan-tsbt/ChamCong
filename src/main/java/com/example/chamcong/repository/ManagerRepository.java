package com.example.chamcong.repository;

import com.example.chamcong.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {

    @Query("select user from User user where user.email=:email")
    Optional<Manager> getManagerByEmail(String email);
}
