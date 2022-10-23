package com.example.chamcong.controller.admin;

import com.example.chamcong.business.admin.AuthAdminBusiness;
import com.example.chamcong.filter.JWTFilter;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.AdminRegisterRequest;
import com.example.chamcong.model.response.AdminLoginResponse;
import com.example.chamcong.security.JWTProvider;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@RequestMapping(value = "/auth/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@CrossOrigin("http://localhost:8082/")
public class AuthAdminController {

    private final AuthAdminBusiness authAdminBusiness;
    private final JWTProvider jwtProvider;
    private final JWTFilter jwtFilter;

    public AuthAdminController(AuthAdminBusiness authAdminBusiness, JWTProvider jwtProvider, JWTFilter jwtFilter) {
        this.authAdminBusiness = authAdminBusiness;
        this.jwtProvider = jwtProvider;
        this.jwtFilter = jwtFilter;
    }

    @PostMapping("/login")
    public RootResponse<AdminLoginResponse> login(@RequestBody @Valid AdminRegisterRequest input, HttpServletResponse response) {
        AdminLoginResponse body  = authAdminBusiness.login(input);
        Cookie cookie = new Cookie(JWTFilter.COOKIE_NAME, body.getToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge((int) Duration.of(1, ChronoUnit.DAYS).toSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
        return RootResponse.success("Login manager success", body);
    }
    @GetMapping("/check_login")
    public RootResponse<AdminLoginResponse>  checkLogin(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        return null;
    }

    @DeleteMapping("/logout")
    public RootResponse<Object> logout(HttpServletResponse response){
        Cookie cookie = new Cookie(JWTFilter.COOKIE_NAME, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return RootResponse.success("Đăng xuất thành công", null);
    }

}
