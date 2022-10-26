package com.example.chamcong.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SearchUserRequest {

    private String searchUser;

    private Integer page = 1;

    private Integer size = 5 ;
}
