package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.Department;
import com.example.chamcong.model.request.DepartmentRequest;

import java.util.List;

public interface CustomerDepartmentRepository {
    List<Department> findAllDepartment(DepartmentRequest input);

    Integer getAllDepartment(DepartmentRequest input);
}
