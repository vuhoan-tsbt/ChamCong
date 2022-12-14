package com.example.chamcong.repository;

import com.example.chamcong.entity.Department;
import com.example.chamcong.repository.sql.CustomerDepartmentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface DepartmentRepository extends JpaRepository<Department, Integer>, CustomerDepartmentRepository {

    @Override
    @Query("select department from Department department where department.id=:id")
    Department getById(Integer id);

    @Query("select depar from Department depar where depar.department = :department")
    Optional<Department> getByDepartment(String department);

    @Query("select department from Department  department ")
    List<Department> getAllByDepartment();
}
