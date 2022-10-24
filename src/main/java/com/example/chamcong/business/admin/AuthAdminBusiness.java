package com.example.chamcong.business.admin;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.entity.User;
import com.example.chamcong.entity.UserLoginHistory;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.request.AdminRegisterRequest;
import com.example.chamcong.model.response.AdminLoginResponse;
import com.example.chamcong.repository.UserLoginHistoryRepository;
import com.example.chamcong.repository.UserRepository;
import com.example.chamcong.security.JWTProvider;
import com.example.chamcong.utils.HashUtils;
import com.example.chamcong.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthAdminBusiness extends BaseBusiness {
    private final UserRepository userRepository;

    private final HashUtils hashUtils;
    private final StringUtils stringUtils;
    private final UserLoginHistoryRepository userLoginHistoryRepository;
    private final JWTProvider jwtProvider;

    public AuthAdminBusiness(UserRepository userRepository,HashUtils hashUtils,StringUtils stringUtils,UserLoginHistoryRepository userLoginHistoryRepository,JWTProvider jwtProvider){
        this.userRepository = userRepository;
        this.hashUtils = hashUtils;
        this.stringUtils =stringUtils;
        this.userLoginHistoryRepository = userLoginHistoryRepository;
        this.jwtProvider = jwtProvider;

    }
    public AdminLoginResponse login(AdminRegisterRequest input) {
        User user = userRepository.getByEmail(input.getEmail())
                .filter(x -> hashUtils.check(input.getPassword(), x.getPassword()))

                .orElseThrow(() -> new DataNotFoundException("Tai khoản mat khẩu không đúng"));
        AdminLoginResponse adminLoginResponse = new AdminLoginResponse()
                .setToken(jwtProvider.generateToken(user))
                .setRefreshToken(stringUtils.randomString());
        userLoginHistoryRepository.save(new UserLoginHistory()
                .setUser(user)
                .setToken(adminLoginResponse.getToken())
                .setRefreshToken(adminLoginResponse.getRefreshToken()));
        return adminLoginResponse;
    }

    public AdminLoginResponse checkLogin(String email) {
        User user = userRepository.getByEmailToken(email);
        if (user == null){
            throw  new DataNotFoundException(" Phiên đăng nhập đã hết hạn.Vui lòng đăng nhập lại");
        }
        AdminLoginResponse adminLoginResponse = new AdminLoginResponse();
        adminLoginResponse.setToken(jwtProvider.generateToken(user));
        return adminLoginResponse;
    }
}
