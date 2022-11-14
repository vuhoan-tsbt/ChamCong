package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.Position;
import com.example.chamcong.model.request.PositionRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerPositionRepositoryImpl implements CustomerPositionRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Position> findByAllPosition(PositionRequest input) {
        String sql = "select * from position where 1=1 ";
        if (input.getPosition() != null && !input.getPosition().trim().equals("")) {
            sql += " and position like '%" + input.getPosition() + "%'";
        }
        if (input.getPage() != null) {
            int pageOffset = 5 * (input.getPage() - 1);
            sql += " limit 5 offset " + pageOffset;
        }
        return entityManager.createNativeQuery(sql, Position.class).getResultList();
    }

    @Override
    public Integer getAllPosition(PositionRequest input) {
        String sql = "select count(*) from position where 1=1 ";
        if (input.getPosition() != null && !input.getPosition().trim().equals("")) {
            sql += " and position like '%" + input.getPosition() + "%'";
        }
        Query query = entityManager.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }
}
