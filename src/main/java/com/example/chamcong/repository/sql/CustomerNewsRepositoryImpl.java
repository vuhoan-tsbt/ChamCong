package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.News;
import com.example.chamcong.model.request.NewsRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerNewsRepositoryImpl implements CustomerNewsRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<News> findAllNews(NewsRequest input) {
        String sql = " select * from news where 1=1";
        if (input.getTypeOfNews() != null && !input.getTypeOfNews().trim().equals("")) {
            sql += " and type_of_news like '%" + input.getTypeOfNews() + "%'";
        }
        return entityManager.createNativeQuery(sql, News.class).getResultList();
    }

    @Override
    public Integer getAllNews(NewsRequest input) {
        String sql = " select count(*) from news where 1=1";

        if (input.getTypeOfNews() != null && !input.getTypeOfNews().trim().equals("")) {
            sql += " and type_of_news like '%" + input.getTypeOfNews() + "%'";
        }
        Query query = entityManager.createNativeQuery(sql);
        int count = ((Number) query.getSingleResult()).intValue();
        return count;
    }
}
