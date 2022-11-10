package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.News;
import com.example.chamcong.model.request.NewsRequest;

import java.util.List;

public interface CustomerNewsRepository {

    List<News> findAllNews(NewsRequest input);

    Integer getAllNews(NewsRequest input);
}
