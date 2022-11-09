package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.User;

import com.example.chamcong.model.request.SearchUserRequest;

import java.util.List;

public interface CustomerUserRepository {

    List<User> findAllByCondition(SearchUserRequest input);

    Integer getAllByCondition(SearchUserRequest input);

    User getSalary(String staffCode);

    List<User> getAllUserDepartment(Integer id);

    List<User> getAllUserPosition(Integer id);


}
