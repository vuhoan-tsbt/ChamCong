package com.example.chamcong.business.staff;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.entity.News;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.request.CreateNewsRequest;
import com.example.chamcong.model.request.NewsRequest;
import com.example.chamcong.model.response.IdNewsResponse;
import com.example.chamcong.model.response.NewsResponse;
import com.example.chamcong.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsBusiness extends BaseBusiness {

    private final NewsRepository newsRepository;

    public NewsBusiness(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public PageResponse<NewsResponse> listNews(NewsRequest input) {
        List<News> news = newsRepository.findAllNews(input);
        int totalElements = newsRepository.getAllNews(input);
        int totalPages = Math.max(1, (int) Math.ceil((double) totalElements / input.getSize()));
        List<NewsResponse> responses = news.stream().map(news1 -> {
            NewsResponse newsResponse = new NewsResponse();
            newsResponse.setId(news1.getId());
            newsResponse.setTypeOfNews(news1.getTypeOfNews());
            newsResponse.setContent(news1.getContent());
            newsResponse.setCreatedAt(String.valueOf(news1.getCreatedAt()));
            return newsResponse;
        }).collect(Collectors.toList());
        return PageResponse.create(totalPages,totalElements,responses);
    }

    public IdNewsResponse createNews(CreateNewsRequest input) {
        Optional<News> optional = newsRepository.getByName(input.getTypeOfNews());
        if (optional.isPresent()){
            throw new DataNotFoundException("Tin tức này đã tồn tại");
        }
        News news = new News();
        news.setTypeOfNews(input.getTypeOfNews());
        news.setContent(input.getContent());
        newsRepository.save(news);
        return new IdNewsResponse(news.getId());
    }

    public IdNewsResponse updateNews(int id, CreateNewsRequest input) {
        News news = newsRepository.getById(id);
        if(news == null){
            throw new DataNotFoundException("Tin tức này không tồn tại");
        }
        news.setTypeOfNews(input.getTypeOfNews());
        news.setContent(input.getContent());
        newsRepository.save(news);
        return new IdNewsResponse(news.getId());
    }

    public IdNewsResponse deleteNews(int id) {
        News news = newsRepository.getById(id);
        if(news == null){
            throw new DataNotFoundException("Tin tức này không tồn tại");
        }
        newsRepository.delete(news);
        return new IdNewsResponse(news.getId());
    }
}
