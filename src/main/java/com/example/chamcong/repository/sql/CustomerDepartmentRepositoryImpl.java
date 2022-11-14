package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.Department;
import com.example.chamcong.model.request.DepartmentRequest;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerDepartmentRepositoryImpl implements CustomerDepartmentRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Department> findAllDepartment(DepartmentRequest input) {
        String sql = "select * from department where 1=1 ";
        if (input.getDepartment() != null && !input.getDepartment().trim().equals("")) {
            sql += " and department like '%" + input.getDepartment() + "%'";
        }
        if (input.getPage() != null) {
            int pageOffset = 5 * (input.getPage() - 1);
            sql += " limit 5 offset " + pageOffset;
        }
        return entityManager.createNativeQuery(sql, Department.class).getResultList();
    }

    @Override
    public Integer getAllDepartment(DepartmentRequest input) {
        String sql = "select count(*) from department where 1=1";
        if (input.getDepartment() != null && !input.getDepartment().trim().equals("")) {
            sql += " and department like '%" + input.getDepartment() + "%'";
        }
        Query query = entityManager.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }
}
