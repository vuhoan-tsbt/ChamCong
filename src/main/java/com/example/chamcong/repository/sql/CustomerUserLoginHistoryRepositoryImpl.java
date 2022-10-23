package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.UserLoginHistory;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerUserLoginHistoryRepositoryImpl implements CustomerUserLoginHistoryRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<UserLoginHistory> finAllUserLoginHistory(Integer id) {
        String sql = " select * from user_login_history where user_id = " + id;

        return entityManager.createNativeQuery(sql, UserLoginHistory.class).getResultList();
    }
}
