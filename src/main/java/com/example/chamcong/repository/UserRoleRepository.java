package com.example.chamcong.repository;

import com.example.chamcong.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query("select role from UserRole  role where role.id = :id")
    UserRole getById(Integer id);
}
