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
            throw new DataNotFoundException("T??i Kho???n Kh??ng T???n T???i");
        } else {
            User user = optUser.get();
            if (!hashUtils.check(input.getPassword(), user.getPassword())) {
                throw new DataNotFoundException("M???t kh???u kh??ng ????ng");
            }
            if (user.getStatus().equals(AccStatusEnum.CREATED.getValue())) {
                throw new DataNotFoundException("T??i Kho???n c???a b???n ??ang b??? kh??a, vui l??ng li??n h??? qu???n tr??? vi??n");
            }
            if (user.getStatus().equals(AccStatusEnum.BANNED.getValue())) {
                throw new DataNotFoundException("T??i kho???n c???a b???n ???? b??? c???m");
            }
            LoginResponse loginResponse = new LoginResponse()
                    .setToken(jwtProvider.generateToken(user))
                    .setRefreshToken(stringUtils.randomString())
                    .setId(user.getId())
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
            throw new DataNotFoundException(" Phi??n ????ng nh???p ???? h???t h???n.Vui l??ng ????ng nh???p l???i");
        }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtProvider.generateToken(user));
        return loginResponse;
    }

    public void sendUrlResetPassword(String email) throws MessagingException, UnsupportedEncodingException {
        Optional<User> opUser = userRepository.findByEmail(email);
        if (opUser.isEmpty()) {
            throw new DataNotFoundException("Ng?????i d??ng kh??ng t???n t???i");
        }
        User user = opUser.get();
        // send mail
        String token = jwtProvider.generateTokenEmail(email);
        user.setResetPasswordToken(token);
        userRepository.save(user);
        mailService.sendMail(String.format("http://localhost:8000/reset-pass/%s/%s", email, token), email);
    }

    public void resetPassword(ResetPasswordTokenRequest input) {
        Optional<User> optUser = userRepository.findByEmail(input.getEmail());
        if (optUser.isEmpty()) {
            throw new DataNotFoundException("Ng?????i D??ng kh??ng t???n t???i");
        }
        User user = optUser.get();
        if (!user.getResetPasswordToken().equals(input.getToken())) {
            throw new DataNotFoundException("Token kh??ng ????ng v???i email y??u c???u");
        }
        String pass = hashUtils.hash(input.getPassword());
        user.setPassword(pass);
        userRepository.save(user);
    }

    public IdResponse forgotPasswordOTP(ForgotPasswordOTPRequest input) {
        Optional<User> optUser = userRepository.getByPhone(input.getPhone());
        if (optUser.isEmpty()) {
            throw new DataNotFoundException("Ng?????i d??ng kh??ng t???n t???i");
        } else {
            User user = optUser.get();
            if (user.getStatus().equals(AccStatusEnum.CREATED.getValue())) {
                throw new DataNotFoundException("T??i Kho???n c???a b???n ??ang b??? kh??a, c???n m??? kh??a tr?????c khi l???y l???i m???t kh???u, h??y li??n h??? qu???n tr??? vi??n");
            }
            if (user.getStatus().equals(AccStatusEnum.BANNED.getValue())) {
                throw new DataNotFoundException("T??i kho???n c???a b???n ???? b??? c???m");
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
            throw new DataNotFoundException("Ng?????i d??ng kh??ng t???n t???i");
        } else {
            User user = optUser.get();
            if (user.getStatus().equals(AccStatusEnum.CREATED.getValue())) {
                throw new DataNotFoundException("T??i Kho???n c???a b???n ??ang b??? kh??a, c???n m??? kh??a tr?????c khi l???y l???i m???t kh???u, h??y li??n h??? qu???n tr??? vi??n");
            }
            if (user.getStatus().equals(AccStatusEnum.BANNED.getValue())) {
                throw new DataNotFoundException("T??i kho???n c???a b???n ???? b??? c???m");
            }
        }
        Optional<OTP> optOtp = otpRepository.getByTargetId(input.getId());
        if (optOtp.isEmpty()) {
            throw new DataNotFoundException("m?? opt tr???ng");
        } else {
            OTP otp = optOtp.get();
            if (!otp.getOtp().equals(input.getOtp())) {
                throw new DataNotFoundException("M?? otp kh??ng ????ng v???i m?? otp ???? g???i");
            }
            if (!otp.getCreatedAt().plusHours(4).isAfter(LocalDateTime.now())) {
                throw new DataNotFoundException("M?? otp ???? h???t h???n ");
            }
        }
        userRepository.save(optUser.get().setPassword(hashUtils.hash(input.getPassword())));
        return new IdResponse(optUser.get().getId());

    }
}
