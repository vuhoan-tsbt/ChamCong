package com.example.chamcong.controller.staff;


import com.example.chamcong.business.staff.AuthStaffBusiness;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.ForgotPasswordStaffRequest;
import com.example.chamcong.model.request.LoginStaffRequest;
import com.example.chamcong.model.request.StaffResetPasswordTokenRequest;
import com.example.chamcong.model.response.LoginStaffResponse;
import com.example.chamcong.model.response.StaffResetPasswordTokenResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RequestMapping(value = "/auth/staff/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@CrossOrigin("http://localhost:8080/")
public class AuthStaffController {
    private final AuthStaffBusiness authStaffBusiness ;

    public AuthStaffController(AuthStaffBusiness authStaffBusiness) {
        this.authStaffBusiness = authStaffBusiness;
    }

    @PostMapping("/login")
    public RootResponse<LoginStaffResponse> loginStaff(@RequestBody @Valid LoginStaffRequest input) {
        return RootResponse.success("", authStaffBusiness.loginStaff(input));
    }

    @PostMapping("/forgot_password")
    public RootResponse<String> sendUrlResetPassword(@RequestBody @Valid ForgotPasswordStaffRequest input) throws MessagingException, UnsupportedEncodingException {
        authStaffBusiness.sendUrlResetPassword(input.getEmail());
        return RootResponse.success("Thông tin đã được gửi đến gmail của bạn", null);
    }
    @PostMapping("/reset_password")
    public RootResponse<StaffResetPasswordTokenResponse> resetPassword(@RequestBody @Valid StaffResetPasswordTokenRequest input) {
        authStaffBusiness.resetPassword(input);
        return RootResponse.success("Đổi Mật khẩu thành công, bạn hãy đăng nhập lại", null);
    }



}
