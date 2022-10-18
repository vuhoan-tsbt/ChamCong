package com.example.chamcong.repository.sql;

import com.example.chamcong.dto.TimeKeepingDTO;
import com.example.chamcong.entity.User;
import com.example.chamcong.model.request.DotInformationRequest;
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
        if (input.getEmail() != null && !input.getEmail().trim().equals("")) {
            sql += " AND email like '%" + input.getEmail() + "%'";
        }
        if ((input.getName() != null && !input.getName().trim().equals(""))) {
            sql += " AND full_name   like '%" + input.getName() + "%'";
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
        if (input.getEmail() != null && !input.getEmail().trim().equals("")) {
            sql += " AND email like '%" + input.getEmail() + "%'";
        }
        if ((input.getName() != null && !input.getName().trim().equals(""))) {
            sql += " AND full_name   like '%" + input.getName() + "%'";
        }
        Query query = entityManager.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }


}
