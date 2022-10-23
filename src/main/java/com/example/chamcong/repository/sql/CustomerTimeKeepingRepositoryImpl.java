package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.TimeKeeping;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerTimeKeepingRepositoryImpl implements CustomerTimeKeepingRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<TimeKeeping> findByIdUser(Integer id) {
        String sql = "select * from timekeeping where user_id = " + id;

        return entityManager.createNativeQuery(sql, TimeKeeping.class).getResultList();
    }
}
