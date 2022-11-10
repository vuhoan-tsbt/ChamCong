package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.Policy;
import com.example.chamcong.model.request.PolicyRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerPolicyRepositoryImpl implements CustomerPolicyRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Policy> findAllPolicy(PolicyRequest input) {
        String sql = "select * from policy where 1=1 ";
        if (input.getNamePolicy() != null && !input.getNamePolicy().trim().equals("")) {

            sql += " and name_policy like '%" +input.getNamePolicy()+ "%' ";
        }
        if (input.getPage() != null) {
            int pageOffset = 5 * (input.getPage() - 1);
            sql += " limit 5 offset " + pageOffset;
        }

        return entityManager.createNativeQuery(sql, Policy.class).getResultList();
    }

    @Override
    public Integer getAllPolicy(PolicyRequest input) {
        String sql = "select count(*) from policy where 1=1 ";
        if (input.getNamePolicy() != null && !input.getNamePolicy().trim().equals("")) {

            sql += " and name_policy like '%" +input.getNamePolicy()+ "%' ";
        }
        Query query = entityManager.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }
}
