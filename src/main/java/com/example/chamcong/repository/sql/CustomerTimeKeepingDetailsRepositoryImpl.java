package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.TimeKeepingDetails;
import com.example.chamcong.model.request.DotInformationRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerTimeKeepingDetailsRepositoryImpl implements CustomerTimeKeepingDetailsRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<TimeKeepingDetails> getByALlTimeKeeping(DotInformationRequest input) {

        String sql = "select * from timekeeping_details a inner join timekeeping b on a.timekeeping_id = b.id inner join user c on b.user_id = c.id where b.months = '" + input.getMonth() + "' and c.staff_code = '" + input.getStaffCode() + "'";

        return entityManager.createNativeQuery(sql, TimeKeepingDetails.class).getResultList();
    }


}
