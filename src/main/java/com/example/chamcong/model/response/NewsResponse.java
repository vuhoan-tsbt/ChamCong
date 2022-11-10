package com.example.chamcong.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NewsResponse {
    private Integer id;

    private String createdAt;

    private String content;

    private String typeOfNews;
}
