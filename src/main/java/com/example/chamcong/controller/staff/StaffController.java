package com.example.chamcong.controller.staff;

import com.example.chamcong.business.staff.StaffBusiness;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.ChainPasswordRequest;
import com.example.chamcong.model.request.StaffUpdateInformationRequest;
import com.example.chamcong.model.response.IdResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = "/staff/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@PreAuthorize("hasAnyAuthority('STAFF','EDITER')")
public class StaffController {

    private final StaffBusiness staffBusiness;

    public StaffController(StaffBusiness staffBusiness) {
        this.staffBusiness = staffBusiness;
    }

    @PostMapping("/information")
    public RootResponse<IdResponse> staffUpdateInformation(@RequestBody @Valid StaffUpdateInformationRequest input){
        return RootResponse.success("Cập nhật thông tin thành công",staffBusiness.staffUpdateInformation(input));
    }

    @PostMapping("/chain_password")
    public RootResponse<IdResponse> chainPassword(@RequestBody @Valid ChainPasswordRequest input) {
        return RootResponse.success("Đổi Mật Khẩu Thành Công.", staffBusiness.chainPassword(input));
    }


}
