package com.example.chamcong.repository;

import com.example.chamcong.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("select department from Department department where department.id=:id")
    Department getById(Integer id);
}
