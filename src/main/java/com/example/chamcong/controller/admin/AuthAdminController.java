package com.example.chamcong.controller.admin;

import com.example.chamcong.business.admin.AuthAdminBusiness;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.AdminRegisterRequest;
import com.example.chamcong.model.response.AdminLoginResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = "/auth/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AuthAdminController {

    private final AuthAdminBusiness authAdminBusiness;

    public AuthAdminController(AuthAdminBusiness authAdminBusiness) {
        this.authAdminBusiness = authAdminBusiness;
    }

    @PostMapping("/manger/login")
    public RootResponse<AdminLoginResponse> login(@RequestBody @Valid AdminRegisterRequest input) {
        return RootResponse.success("Login manager success", authAdminBusiness.login(input));
    }

}
