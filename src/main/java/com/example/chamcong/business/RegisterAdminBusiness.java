package com.example.chamcong.business;

import com.example.chamcong.entity.Manager;
import com.example.chamcong.entity.User;
import com.example.chamcong.entity.UserRole;
import com.example.chamcong.enumtation.AccStatusEnum;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.response.IdResponse;
import com.example.chamcong.model.request.AdminRegisterRequest;
import com.example.chamcong.repository.ManagerRepository;
import com.example.chamcong.repository.UserRepository;
import com.example.chamcong.utils.HashUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterAdminBusiness extends BaseBusiness{

    private final ManagerRepository managerRepository;
    private final HashUtils hashUtils;
    private final UserRepository userRepository;

    public RegisterAdminBusiness(ManagerRepository managerRepository, HashUtils hashUtils, UserRepository userRepository){
        this.managerRepository = managerRepository;
        this.hashUtils = hashUtils;
        this.userRepository = userRepository;
    }

    public String setNumbers(long number) {
        int count = 0;
        long num = number;
        while (num > 0) {
            num /= 10;
            count++;
        }
        String out = "";
        while (6 - count > 0) {
            out = out + "0";
            count++;
        }
        return out + number;
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    public IdResponse register(AdminRegisterRequest input) {
        Optional<Manager> optManager = managerRepository.getManagerByEmail(input.getEmail());
        if (optManager.isPresent()) {
            throw new DataNotFoundException("email đã được đăng ký vui lòng nhập email khác");
        }
        Manager user = managerRepository.save((Manager) (new Manager()
                .setEmail(input.getEmail())
                .setPassword(hashUtils.hash(input.getPassword()))
                .setStaffCode("MNGR" + setNumbers(userRepository.countUser() + 1)))
                .setRole(new UserRole(1, "ADMIN")));
        userRepository.save((User) user.setStatus(AccStatusEnum.ACTIVATED));
        return new IdResponse(user.getId());
    }
}
