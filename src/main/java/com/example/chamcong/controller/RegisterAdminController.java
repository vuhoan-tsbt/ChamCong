package com.example.chamcong.controller;

import com.example.chamcong.business.RegisterAdminBusiness;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.response.IdResponse;
import com.example.chamcong.model.request.AdminRegisterRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = "/user/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class RegisterAdminController {

    private RegisterAdminBusiness registerAdminBusiness;

    public RegisterAdminController(RegisterAdminBusiness registerAdminBusiness){

        this.registerAdminBusiness = registerAdminBusiness;
    }

    @PostMapping("/register")
    public RootResponse<IdResponse> register(@RequestBody @Valid AdminRegisterRequest input) {
        return RootResponse.success("register user success", registerAdminBusiness.register(input));
    }
}
