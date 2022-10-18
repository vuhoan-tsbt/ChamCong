package com.example.chamcong.repository.sql;

import com.example.chamcong.dto.TimeKeepingDTO;
import com.example.chamcong.entity.User;
import com.example.chamcong.model.request.DotInformationRequest;
import com.example.chamcong.model.request.SearchUserRequest;

import java.util.List;

public interface CustomerUserRepository {
    List<User> findAllByCondition(SearchUserRequest input);

    Integer getAllByCondition(SearchUserRequest input);


}
