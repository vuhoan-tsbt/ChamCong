package com.example.chamcong.business.admin;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.entity.Policy;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.request.CreatePolicyRequest;
import com.example.chamcong.model.request.PolicyRequest;
import com.example.chamcong.model.response.IdPolicyResponse;
import com.example.chamcong.model.response.PolicyResponse;
import com.example.chamcong.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PolicyBusiness extends BaseBusiness {

    private final PolicyRepository policyRepository;

    public PolicyBusiness(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public PageResponse<PolicyResponse> listPolicy(PolicyRequest input) {
        List<Policy> policies = policyRepository.findAllPolicy(input);
        int totalElements = policyRepository.getAllPolicy(input);
        int totalPages = Math.max(1, (int) Math.ceil((double) totalElements / input.getSize()));
        List<PolicyResponse> responses = policies.stream().map(policy -> {
            PolicyResponse policyResponse = new PolicyResponse();
            policyResponse.setId(policy.getId());
            policyResponse.setNamePolicy(policy.getNamePolicy());
            policyResponse.setContentPolicy(policy.getContentPolicy());
            return policyResponse;
        }).collect(Collectors.toList());
        return PageResponse.create(totalPages,totalElements,responses);
    }

    public IdPolicyResponse createPolicy(CreatePolicyRequest input) {
        Optional<Policy> optPolicy = policyRepository.getByName(input.getNamePolicy());
        if(optPolicy.isPresent()){
            throw new DataNotFoundException("Chính sách này đã tồn tại");
        }
        Policy policy = new Policy();
        policy.setNamePolicy(input.getNamePolicy());
        policy.setContentPolicy(input.getContentPolicy());
        policyRepository.save(policy);
        return new IdPolicyResponse(policy.getId());
    }

    public IdPolicyResponse updatePolicy(int id, CreatePolicyRequest input) {
        Optional<Policy> optPolicy = policyRepository.findById(id);
        if (optPolicy.isEmpty()){
            throw new DataNotFoundException("Chính sách không tồn tại");
        }
        optPolicy.get().setNamePolicy(input.getNamePolicy());
        optPolicy.get().setContentPolicy(input.getContentPolicy());
        policyRepository.save(optPolicy.get());
        return new IdPolicyResponse(optPolicy.get().getId());
    }

    public IdPolicyResponse deletePolicy(int id) {
        Optional<Policy> optPolicy = policyRepository.findById(id);
        if (optPolicy.isEmpty()){
            throw new DataNotFoundException("Chính sách không tồn tại");
        }
        policyRepository.delete(optPolicy.get());
        return new IdPolicyResponse(optPolicy.get().getId());
    }
}
