package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.Salary;
import com.example.chamcong.model.request.ListSalaryEmployeeRequest;

import java.util.List;

public interface CustomerSalaryRepository {
    List<Salary> getAllSalaryUser(ListSalaryEmployeeRequest input);

    Integer findAllSalaryUser(ListSalaryEmployeeRequest input);

    List<Salary> getSalaryUser(Integer userId);
}
