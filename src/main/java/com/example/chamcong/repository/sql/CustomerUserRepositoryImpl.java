package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.User;
import com.example.chamcong.model.request.SearchUserRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerUserRepositoryImpl implements CustomerUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAllByCondition(SearchUserRequest input) {
        String sql = "select * from user WHERE 1=1";
        if (input.getSearchUser() != null && !input.getSearchUser().trim().equals("")) {
            sql += " AND email like '%" + input.getSearchUser() + "%'" + " OR " + " full_name   like '%" + input.getSearchUser() + "%'" ;
        }

        sql += " order by created_at desc";

        if (input.getPage() != null) {
            int pageOffset = 5 * (input.getPage() - 1);
            sql += " limit 5 offset " + pageOffset;
        }
        return entityManager.createNativeQuery(sql, User.class).getResultList();
    }

    @Override
    public Integer getAllByCondition(SearchUserRequest input) {
        String sql = "select count(*) from user WHERE 1=1";
        if (input.getSearchUser() != null && !input.getSearchUser().trim().equals("")) {
            sql += " AND email like '%" + input.getSearchUser() + "%'" + " OR " + " full_name   like '%" + input.getSearchUser() + "%'" ;
        }

        Query query = entityManager.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }

    @Override
    public User getSalary(String staffCode) {
        String sql = " select * from user a inner join position b on a.position_id = b.id where a.staff_code = '" + staffCode +"'";

        Query query = entityManager.createNativeQuery(sql, User.class);
        return (User) query.getSingleResult();

    }

    @Override
    public List<User> getAllUserDepartment(Integer id) {

        String sql = "select * from user where department_id = " + id;

        return entityManager.createNativeQuery(sql, User.class).getResultList();
    }


}
