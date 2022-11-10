package com.example.chamcong.controller.staff;

import com.example.chamcong.business.staff.NewsBusiness;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.CreateNewsRequest;
import com.example.chamcong.model.request.NewsRequest;
import com.example.chamcong.model.response.IdNewsResponse;
import com.example.chamcong.model.response.NewsResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/news/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@PreAuthorize("hasAnyAuthority('EDITER')")
public class NewsController {

    private final NewsBusiness newsBusiness;

    public NewsController(NewsBusiness newsBusiness) {
        this.newsBusiness = newsBusiness;
    }

    @GetMapping("/list-news")
    public RootResponse<PageResponse<NewsResponse>> listNews(@ModelAttribute NewsRequest input){
        return RootResponse.success("Danh sách tin tức",newsBusiness.listNews(input));
    }
    @PostMapping("/create-news")
    public RootResponse<IdNewsResponse> createNews(@RequestBody @Valid CreateNewsRequest input){
        return RootResponse.success("Thêm tin tức thành công",newsBusiness.createNews(input));
    }
    @PatchMapping("/update-news/{id}")
    public RootResponse<IdNewsResponse> updateNews(@PathVariable("id") int id, @RequestBody @Valid CreateNewsRequest input){
        return RootResponse.success("Sửa tin tức thành công",newsBusiness.updateNews(id,input));
    }
    @DeleteMapping("/delete-news/{id}")
    public RootResponse<IdNewsResponse> deleteNews(@PathVariable("id") int id){
        return RootResponse.success("Xóa tin tức thành công",newsBusiness.deleteNews(id));
    }


}
