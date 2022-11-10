package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.Salary;
import com.example.chamcong.model.request.ListSalaryEmployeeRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerSalaryRepositoryImpl implements CustomerSalaryRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Salary> getAllSalaryUser(ListSalaryEmployeeRequest input) {

        String sql = "select * from  salary a inner join user b on a.user_id = b.id where month(months) = " +input.getMonths() ;

        if (input.getPage() != null) {
            int pageOffset = 5 * (input.getPage() - 1);
            sql += " limit 5 offset " + pageOffset;
        }
        return entityManager.createNativeQuery(sql, Salary.class).getResultList();
    }

    @Override
    public Integer findAllSalaryUser(ListSalaryEmployeeRequest input) {

        String sql = "select count(*) from  salary a inner join user b on a.user_id = b.id where month(months) = " +input.getMonths() ;


        Query query = entityManager.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;

    }
}
