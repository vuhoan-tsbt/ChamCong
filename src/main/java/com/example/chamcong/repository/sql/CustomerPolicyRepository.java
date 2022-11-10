package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.Policy;
import com.example.chamcong.model.request.PolicyRequest;

import java.util.List;

public interface CustomerPolicyRepository {
    List<Policy> findAllPolicy(PolicyRequest input);

    Integer getAllPolicy(PolicyRequest input);
}
