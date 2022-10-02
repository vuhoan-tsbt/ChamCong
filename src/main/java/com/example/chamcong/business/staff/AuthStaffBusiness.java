package com.example.chamcong.business.staff;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.entity.Employee;
import com.example.chamcong.entity.User;
import com.example.chamcong.entity.UserLoginHistory;
import com.example.chamcong.enumtation.AccStatusEnum;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.request.LoginStaffRequest;
import com.example.chamcong.model.response.LoginStaffResponse;
import com.example.chamcong.repository.UserLoginHistoryRepository;
import com.example.chamcong.repository.UserRepository;
import com.example.chamcong.security.JWTProvider;
import com.example.chamcong.utils.CheckerUtils;
import com.example.chamcong.utils.HashUtils;
import com.example.chamcong.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthStaffBusiness extends BaseBusiness {

    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;
    private final StringUtils stringUtils;
    private final UserLoginHistoryRepository userLoginHistoryRepository;
    private final HashUtils hashUtils;

    public AuthStaffBusiness(UserRepository userRepository, JWTProvider jwtProvider, StringUtils stringUtils, UserLoginHistoryRepository userLoginHistoryRepository, HashUtils hashUtils) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.stringUtils = stringUtils;
        this.userLoginHistoryRepository = userLoginHistoryRepository;
        this.hashUtils = hashUtils;
    }

    public LoginStaffResponse loginStaff(LoginStaffRequest input) {
        User user = userRepository.getByStaffCode(input.getStaffCode())
                .filter(x -> hashUtils.check(input.getPassword(), x.getPassword()))
                .filter(x -> AccStatusEnum.ACTIVATED.equals(x.getStatus()))
                .orElseThrow(() -> new DataNotFoundException("Tai khoản mat khẩu không đúng"));
        LoginStaffResponse response = new LoginStaffResponse()
                .setToken(jwtProvider.generateToken(user))
                .setRefreshToken(stringUtils.randomString());
        userLoginHistoryRepository.save(new UserLoginHistory()
                .setUser(user)
                .setToken(response.getToken())
                .setRefreshToken(response.getRefreshToken()));
        return response;
    }
}
