package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.TimeKeeping;
import com.example.chamcong.model.request.DotInformationRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Override
    public TimeKeeping getEntryTime(Integer userId) {
        String sql = "select * from timekeeping where day(entry_time) = day(now()) and user_id = " + userId;
        Query query = entityManager.createNativeQuery(sql,TimeKeeping.class);
        return (TimeKeeping) query.getSingleResult();
    }

    @Override
    public TimeKeeping getTimeKeeping(Integer id) {
        String sql = "select * from user a left join timekeeping b on a.id = b.user_id where a.id = " + id;
        Query query = entityManager.createNativeQuery(sql,TimeKeeping.class);
        return (TimeKeeping) query.getSingleResult();
    }

    @Override
    public List<TimeKeeping> getByALlTimeKeeping(DotInformationRequest input) {

        String sql = "select * from  timekeeping a  inner join user b on a.user_id = b.id where a.months = '" + input.getMonth() + "' and b.staff_code = '" + input.getStaffCode() + "'";

        return entityManager.createNativeQuery(sql, TimeKeeping.class).getResultList();
    }


}
