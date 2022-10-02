package com.example.chamcong.controller.staff;

import com.example.chamcong.business.admin.AuthAdminBusiness;
import com.example.chamcong.business.staff.AuthStaffBusiness;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.LoginStaffRequest;
import com.example.chamcong.model.response.LoginStaffResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = "/auth/staff/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AuthStaffController {
    private final AuthStaffBusiness authStaffBusiness ;

    public AuthStaffController(AuthStaffBusiness authStaffBusiness) {
        this.authStaffBusiness = authStaffBusiness;
    }

    @PostMapping("/login")
    public RootResponse<LoginStaffResponse> loginStaff(@RequestBody @Valid LoginStaffRequest input) {
        return RootResponse.success("", authStaffBusiness.loginStaff(input));
    }

}
