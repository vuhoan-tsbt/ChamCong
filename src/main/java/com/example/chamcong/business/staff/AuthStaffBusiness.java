package com.example.chamcong.business.staff;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.entity.Employee;
import com.example.chamcong.entity.User;
import com.example.chamcong.entity.UserLoginHistory;
import com.example.chamcong.enumtation.AccStatusEnum;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.request.LoginStaffRequest;
import com.example.chamcong.model.request.StaffResetPasswordTokenRequest;
import com.example.chamcong.model.response.LoginStaffResponse;
import com.example.chamcong.repository.UserLoginHistoryRepository;
import com.example.chamcong.repository.UserRepository;
import com.example.chamcong.security.JWTProvider;
import com.example.chamcong.service.MailService;
import com.example.chamcong.utils.CheckerUtils;
import com.example.chamcong.utils.HashUtils;
import com.example.chamcong.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public class AuthStaffBusiness extends BaseBusiness {

    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;
    private final StringUtils stringUtils;
    private final UserLoginHistoryRepository userLoginHistoryRepository;
    private final HashUtils hashUtils;
    private final MailService mailService;

    public AuthStaffBusiness(UserRepository userRepository, JWTProvider jwtProvider, StringUtils stringUtils, UserLoginHistoryRepository userLoginHistoryRepository, HashUtils hashUtils, MailService mailService) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.stringUtils = stringUtils;
        this.userLoginHistoryRepository = userLoginHistoryRepository;
        this.hashUtils = hashUtils;
        this.mailService = mailService;
    }

    public LoginStaffResponse loginStaff(LoginStaffRequest input) {
        Optional<Employee> optEmployee = userRepository.getByStaffCode(input.getStaffCode());
        if (optEmployee.isEmpty()) {
            throw new DataNotFoundException("Tài Khoản Không tồn tại");
        } else {
            Employee employee = optEmployee.get();
            if (!hashUtils.check(input.getPassword(), employee.getPassword())) {
                throw new DataNotFoundException("Mật khẩu không đúng");
            }
            if (employee.getStatus().equals(AccStatusEnum.CREATED.getValue())) {
                throw new DataNotFoundException("Tài Khoản của bạn đang bị khóa, vui lòng liên hệ quản trị viên");
            }
            if (employee.getStatus().equals(AccStatusEnum.BANNED.getValue())) {
                throw new DataNotFoundException("Tài khoản của bạn đã bị cấm");
            }
            LoginStaffResponse response = new LoginStaffResponse()
                    .setToken(jwtProvider.generateToken(employee))
                    .setRefreshToken(stringUtils.randomString());
            userLoginHistoryRepository.save(new UserLoginHistory()
                    .setUser(employee)
                    .setToken(response.getToken())
                    .setRefreshToken(response.getRefreshToken()));
            return response;
        }
    }

    public void sendUrlResetPassword(String email) throws MessagingException, UnsupportedEncodingException {
        Optional<User> opUser = userRepository.findByEmail(email);
        if (opUser.isEmpty()) {
            throw new DataNotFoundException("User can't found.");
        }
        User user = opUser.get();
        // send mail
        String token = jwtProvider.generateTokenEmail(email);
        user.setResetPasswordToken(token);
        userRepository.save(user);
        mailService.sendMail(String.format("https://locahost:8081/%s/%s", email, token), email);
    }

    public void resetPassword(StaffResetPasswordTokenRequest input) {
        Optional<User> optUser = userRepository.findByEmail(input.getEmail());
        if (optUser.isEmpty()) {
            throw new DataNotFoundException("Người Dùng không tồn tại");
        }
        User user = optUser.get();
        if (!user.getResetPasswordToken().equals(input.getToken())) {
            throw new DataNotFoundException("Token không đúng với email yêu cầu");
        }
        String pass = hashUtils.hash(input.getPassword());
        user.setPassword(pass);
        userRepository.save(user);
    }
}
