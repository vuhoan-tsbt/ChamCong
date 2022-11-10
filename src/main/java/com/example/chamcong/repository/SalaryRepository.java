package com.example.chamcong.repository;

import com.example.chamcong.entity.Salary;
import com.example.chamcong.repository.sql.CustomerSalaryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer>, CustomerSalaryRepository {

}
