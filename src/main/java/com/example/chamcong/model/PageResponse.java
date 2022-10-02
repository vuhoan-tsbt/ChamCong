package com.example.chamcong.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {
    private Integer totalPages;
    private Integer totalElements;
    private List<T> data;

    private static <T> PageResponse<T> result(Integer totalPages, Integer totalElements, List<T> data) {
        PageResponse<T> result = new PageResponse<T>();
        result.totalPages = totalPages;
        result.totalElements = totalElements;
        result.data = data;
        return result;
    }

    public static <T> PageResponse<T> create(Integer totalPages, Integer totalElements, List<T> data) {
        return result(totalPages, totalElements, data);
    }

}
