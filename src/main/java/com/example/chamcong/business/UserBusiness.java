package com.example.chamcong.business;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.entity.User;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.request.ChainPasswordRequest;
import com.example.chamcong.model.request.UpdateInformationRequest;
import com.example.chamcong.model.response.ProfileUserResponse;
import com.example.chamcong.model.response.IdResponse;
import com.example.chamcong.repository.UserRepository;
import com.example.chamcong.utils.HashUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class
UserBusiness extends BaseBusiness {

    private final UserRepository userRepository;

    private final HashUtils hashUtils;


    public UserBusiness(UserRepository userRepository, HashUtils hashUtils) {
        this.userRepository = userRepository;
        this.hashUtils = hashUtils;
    }


    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public ProfileUserResponse profileEmployee(){
        User user = getCurrentUser();
        ProfileUserResponse response = new ProfileUserResponse();
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setStaffCode(user.getStaffCode());
        response.setAddress(user.getAddress());
        response.setDateOfBirth(user.getDateOfBirth());
        response.setPhone(user.getPhone());
        response.setDepartment(user.getDepartment().getDepartment());
        response.setPosition((user.getPosition().getPosition()));
        return response;
    }

    public IdResponse staffUpdateInformation(UpdateInformationRequest input) {
        User user = getCurrentUser();
        user.setFullName(input.getFullName());
        user.setDateOfBirth(input.getDateOfBirth());
        user.setAddress(input.getAddress());
        user.setPhone(input.getPhone());
        userRepository.save(user);
        return new IdResponse(user.getId());
    }
    public IdResponse chainPassword(ChainPasswordRequest input) {
        User userLogin = getCurrentUser();
        Optional<User> optUser = userRepository.findByIdPassword(userLogin.getId());
        if (optUser.isEmpty()) {
            throw new DataNotFoundException("Đặt lại mật khẩu không thành công");
        }
        if (!hashUtils.check(input.getOldPassword(), optUser.get().getPassword())) {
            throw new DataNotFoundException("Mật khẩu cũ đúng");
        }
        userRepository.save(userLogin.setPassword(hashUtils.hash(input.getNewPassword())));
        return new IdResponse(userLogin.getId());

    }


}
