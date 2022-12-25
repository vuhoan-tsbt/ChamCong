package com.example.chamcong.business;

import com.example.chamcong.entity.NumberOTP;
import com.example.chamcong.entity.OTP;
import com.example.chamcong.entity.User;
import com.example.chamcong.entity.UserLoginHistory;
import com.example.chamcong.enumtation.AccStatusEnum;
import com.example.chamcong.enumtation.OTPTypeEnum;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.request.AdminRegisterRequest;
import com.example.chamcong.model.request.ForgotPasswordOTPRequest;
import com.example.chamcong.model.request.ResetPasswordOTPRequest;
import com.example.chamcong.model.request.ResetPasswordTokenRequest;
import com.example.chamcong.model.response.IdResponse;
import com.example.chamcong.model.response.LoginResponse;
import com.example.chamcong.repository.OTPRepository;
import com.example.chamcong.repository.UserLoginHistoryRepository;
import com.example.chamcong.repository.UserRepository;
import com.example.chamcong.security.JWTProvider;
import com.example.chamcong.service.MailService;
import com.example.chamcong.service.SmsService;
import com.example.chamcong.utils.HashUtils;
import com.example.chamcong.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthBusiness extends BaseBusiness {
    private final UserRepository userRepository;

    private final HashUtils hashUtils;
    private final StringUtils stringUtils;
    private final UserLoginHistoryRepository userLoginHistoryRepository;
    private final JWTProvider jwtProvider;
    private final MailService mailService;
    private final OTPRepository otpRepository;
    private final SmsService smsService;

    public AuthBusiness(UserRepository userRepository, HashUtils hashUtils, StringUtils stringUtils, UserLoginHistoryRepository userLoginHistoryRepository, JWTProvider jwtProvider, MailService mailService, OTPRepository otpRepository, SmsService smsService) {
        this.userRepository = userRepository;

        this.hashUtils = hashUtils;

        this.stringUtils = stringUtils;

        this.userLoginHistoryRepository = userLoginHistoryRepository;

        this.jwtProvider = jwtProvider;

        this.mailService = mailService;

        this.otpRepository = otpRepository;

        this.smsService = smsService;
    }

    public LoginResponse login(AdminRegisterRequest input) {
        Optional<User> optUser = userRepository.getByEmail(input.getEmail());
        if (optUser.isEmpty()) {
            throw new DataNotFoundException("Tài Khoản Không Tồn Tại");
        } else {
            User user = optUser.get();
            if (!hashUtils.check(input.getPassword(), user.getPassword())) {
                throw new DataNotFoundException("Mật khẩu không đúng");
            }
            if (user.getStatus().equals(AccStatusEnum.CREATED.getValue())) {
                throw new DataNotFoundException("Tài Khoản của bạn đang bị khóa, vui lòng liên hệ quản trị viên");
            }
            if (user.getStatus().equals(AccStatusEnum.BANNED.getValue())) {
                throw new DataNotFoundException("Tài khoản của bạn đã bị cấm");
            }
            LoginResponse loginResponse = new LoginResponse()
                    .setToken(jwtProvider.generateToken(user))
                    .setRefreshToken(stringUtils.randomString())
                    .setEmail(user.getEmail())
                    .setFullName(user.getFullName())
                    .setRole(user.getRole().getRole());
            userLoginHistoryRepository.save(new UserLoginHistory()
                    .setUser(user)
                    .setToken(loginResponse.getToken())
                    .setRefreshToken(loginResponse.getRefreshToken()));
            return loginResponse;
        }
    }

    public LoginResponse checkLogin(String email) {
        User user = userRepository.getByEmailToken(email);
        if (user == null) {
            throw new DataNotFoundException(" Phiên đăng nhập đã hết hạn.Vui lòng đăng nhập lại");
        }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtProvider.generateToken(user));
        return loginResponse;
    }

    public void sendUrlResetPassword(String email) throws MessagingException, UnsupportedEncodingException {
        Optional<User> opUser = userRepository.findByEmail(email);
        if (opUser.isEmpty()) {
            throw new DataNotFoundException("Người dùng không tồn tại");
        }
        User user = opUser.get();
        // send mail
        String token = jwtProvider.generateTokenEmail(email);
        user.setResetPasswordToken(token);
        userRepository.save(user);
        mailService.sendMail(String.format("http://locahost:8000/reset-pass/%s/%s", email, token), email);
    }

    public void resetPassword(ResetPasswordTokenRequest input) {
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

    public IdResponse forgotPasswordOTP(ForgotPasswordOTPRequest input) {
        Optional<User> optUser = userRepository.getByPhone(input.getPhone());
        if (optUser.isEmpty()) {
            throw new DataNotFoundException("Người dùng không tồn tại");
        } else {
            User user = optUser.get();
            if (user.getStatus().equals(AccStatusEnum.CREATED.getValue())) {
                throw new DataNotFoundException("Tài Khoản của bạn đang bị khóa, cần mở khóa trước khi lấy lại mật khẩu, hãy liên hệ quản trị viên");
            }
            if (user.getStatus().equals(AccStatusEnum.BANNED.getValue())) {
                throw new DataNotFoundException("Tài khoản của bạn đã bị cấm");
            }
        }
        OTP otp = otpRepository.save(new NumberOTP()
                .setType(OTPTypeEnum.RESET_PASSWORD))
                .setTargetId(optUser.get().getId());
        smsService.sendOTP(input.getPhone(), otp.getOtp());
        return new IdResponse(optUser.get().getId());

    }

    public IdResponse resetPasswordOTP(ResetPasswordOTPRequest input) {
        Optional<User> optUser = userRepository.findById(input.getId());
        if (optUser.isEmpty()) {
            throw new DataNotFoundException("Người dùng không tồn tại");
        } else {
            User user = optUser.get();
            if (user.getStatus().equals(AccStatusEnum.CREATED.getValue())) {
                throw new DataNotFoundException("Tài Khoản của bạn đang bị khóa, cần mở khóa trước khi lấy lại mật khẩu, hãy liên hệ quản trị viên");
            }
            if (user.getStatus().equals(AccStatusEnum.BANNED.getValue())) {
                throw new DataNotFoundException("Tài khoản của bạn đã bị cấm");
            }
        }
        Optional<OTP> optOtp = otpRepository.getByTargetId(input.getId());
        if (optOtp.isEmpty()) {
            throw new DataNotFoundException("mã opt trống");
        } else {
            OTP otp = optOtp.get();
            if (!otp.getOtp().equals(input.getOtp())) {
                throw new DataNotFoundException("Mã otp không đúng với mã otp đã gửi");
            }
            if (!otp.getCreatedAt().plusHours(4).isAfter(LocalDateTime.now())) {
                throw new DataNotFoundException("Mã otp đã hết hạn ");
            }
        }
        userRepository.save(optUser.get().setPassword(hashUtils.hash(input.getPassword())));
        return new IdResponse(optUser.get().getId());

    }
}
