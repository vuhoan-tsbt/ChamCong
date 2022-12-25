package com.example.chamcong.controller.admin;

import com.example.chamcong.business.admin.PolicyBusiness;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.CreatePolicyRequest;
import com.example.chamcong.model.request.PolicyRequest;
import com.example.chamcong.model.response.IdPolicyResponse;
import com.example.chamcong.model.response.PolicyResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/api/policy", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class PolicyController {

    private final PolicyBusiness policyBusiness;

    public PolicyController(PolicyBusiness policyBusiness) {
        this.policyBusiness = policyBusiness;
    }

    @PostMapping("/list-policy")
    public RootResponse<PageResponse<PolicyResponse>> listPolicy(@RequestBody @Valid PolicyRequest input){
        return RootResponse.success("Các chính sách",policyBusiness.listPolicy(input));
    }
    @PostMapping("/create-policy")
    public RootResponse<IdPolicyResponse> createPolicy(@RequestBody @Valid CreatePolicyRequest input){
        return RootResponse.success("Thêm chính sách thành công",policyBusiness.createPolicy(input));
    }
    @PutMapping("/update-policy/{id}")
    public RootResponse<IdPolicyResponse> updatePolicy(@PathVariable("id") int id, @RequestBody @Valid CreatePolicyRequest input){
        return RootResponse.success("Sửa chính sách thành công",policyBusiness.updatePolicy(id,input));
    }
    @DeleteMapping("delete-policy/{id}")
    public RootResponse<IdPolicyResponse> deletePolicy(@PathVariable("id") int id){
        return RootResponse.success("Xóa chính sách thành công",policyBusiness.deletePolicy(id));
    }
}
