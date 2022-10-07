package com.example.chamcong.business.staff;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.entity.User;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.request.ChainPasswordRequest;
import com.example.chamcong.model.request.StaffUpdateInformationRequest;
import com.example.chamcong.model.response.IdResponse;
import com.example.chamcong.repository.UserRepository;
import com.example.chamcong.utils.HashUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffBusiness extends BaseBusiness {

    private final UserRepository userRepository;

    private final HashUtils hashUtils;

    public StaffBusiness(UserRepository userRepository,HashUtils hashUtils) {
        this.userRepository = userRepository;
        this.hashUtils = hashUtils;
    }


    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public IdResponse staffUpdateInformation(StaffUpdateInformationRequest input) {
        User user = getCurrentUser();
        user.setFullName(input.getFullName());
        user.setDateOfBirth(input.getDateOfBirth());
        user.setAddress(input.getAddress());
        user.setPassword(hashUtils.hash(input.getPassword()));
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
