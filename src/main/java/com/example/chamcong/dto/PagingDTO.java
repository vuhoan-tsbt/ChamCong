package com.example.chamcong.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PagingDTO {
    private Integer page = 1;
    private Integer size = 20;
}
