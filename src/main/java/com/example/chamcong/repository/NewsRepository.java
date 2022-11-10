package com.example.chamcong.repository;

import com.example.chamcong.entity.News;
import com.example.chamcong.repository.sql.CustomerNewsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer>, CustomerNewsRepository {

    @Query("select news from News  news where news.typeOfNews = :typeOfNews")
    Optional<News> getByName(String typeOfNews);

    @Query("select news from News news where news.id =:id")
    News getById(Integer id);
}
