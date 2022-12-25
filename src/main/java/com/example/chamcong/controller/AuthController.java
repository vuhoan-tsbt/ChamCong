package com.example.chamcong.controller;

import com.example.chamcong.business.AuthBusiness;
import com.example.chamcong.filter.JWTFilter;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.*;
import com.example.chamcong.model.response.LoginResponse;
import com.example.chamcong.model.response.ResetPasswordTokenResponse;
import com.example.chamcong.security.JWTProvider;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AuthController {

    private final AuthBusiness authBusiness;
    private final JWTProvider jwtProvider;


    public AuthController(AuthBusiness authBusiness, JWTProvider jwtProvider) {
        this.authBusiness = authBusiness;
        this.jwtProvider = jwtProvider;

    }

    @PostMapping("/login")
    public RootResponse<LoginResponse> login(@RequestBody @Valid AdminRegisterRequest input, HttpServletResponse response) {
        LoginResponse body  = authBusiness.login(input);
        Cookie cookie = new Cookie(JWTFilter.COOKIE_NAME, body.getToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge((int) Duration.of(1, ChronoUnit.DAYS).toSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
        return RootResponse.success("Đăng Nhập Thành Công", body);
    }
    @PostMapping("/forgot_password")
    public RootResponse<String> sendUrlResetPassword(@RequestBody @Valid ForgotPasswordRequest input) throws MessagingException, UnsupportedEncodingException {
        authBusiness.sendUrlResetPassword(input.getEmail());
        return RootResponse.success("Thông tin đã được gửi đến gmail của bạn", null);
    }

    @PostMapping("/reset_password")
    public RootResponse<ResetPasswordTokenResponse> resetPassword(@RequestBody @Valid ResetPasswordTokenRequest input) {
        authBusiness.resetPassword(input);
        return RootResponse.success("Đổi Mật khẩu thành công, bạn hãy đăng nhập lại", null);
    }

    @GetMapping("/check_login")
    public RootResponse<LoginResponse>  checkLogin(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(JWTFilter.COOKIE_NAME)) {
                    // lấy token  =  cookie.getValue();
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String email = jwtProvider.getEmailFromJWT(token);
        return RootResponse.success("", authBusiness.checkLogin(email));
    }
    @DeleteMapping("/logout")
    public RootResponse<Object> logout(HttpServletResponse response){
        Cookie cookie = new Cookie(JWTFilter.COOKIE_NAME, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return RootResponse.success("Đăng xuất thành công", null);
    }

    @PostMapping("/forgot-otp")
    public RootResponse<Object> forgotPasswordOTP(@RequestBody @Valid ForgotPasswordOTPRequest input){
        return RootResponse.success("sẽ có cuộc gọi đến bạn hãy chú ý điện thoại",authBusiness.forgotPasswordOTP(input));
    }
    @PostMapping("/reset-otp")
    public RootResponse<Object> resetPasswordOTP(@RequestBody @Valid ResetPasswordOTPRequest input){
        return RootResponse.success("Đổi mật khẩu thành công, bạn hãy đăng nhập lại",authBusiness.resetPasswordOTP(input));
    }

}
