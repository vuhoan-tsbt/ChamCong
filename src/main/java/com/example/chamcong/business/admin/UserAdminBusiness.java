package com.example.chamcong.business.admin;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.entity.Employee;
import com.example.chamcong.entity.User;
import com.example.chamcong.entity.UserRole;
import com.example.chamcong.enumtation.AccStatusEnum;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.request.CreateEmployeeRequest;
import com.example.chamcong.model.request.UpdateEmployeeRequest;
import com.example.chamcong.model.response.IdResponse;
import com.example.chamcong.utils.HashUtils;
import org.dozer.Mapper;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.request.SearchUserRequest;
import com.example.chamcong.model.response.UserResponse;
import com.example.chamcong.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAdminBusiness extends BaseBusiness {

    private final UserRepository userRepository;

    private final Mapper mapper;

    private final HashUtils hashUtils;

    public UserAdminBusiness(UserRepository userRepository, Mapper mapper, HashUtils hashUtils) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.hashUtils = hashUtils;
    }

    public String Numbers(long numbers) {
        int count = 0;
        long num = numbers;
        while (num > 0) {
            num /= 10;
            count++;
        }
        String out = "";
        while (6 - count > 0) {
            out = out + "0";
            count++;
        }
        return out + numbers;
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public PageResponse<UserResponse> listUser(SearchUserRequest input) {
        List<User> listUser = userRepository.findAllByCondition(input);
        int totalElements = userRepository.getAllByCondition(input);
        int totalPages = Math.max(1, (int) Math.ceil((double) totalElements / input.getSize()));
        List<UserResponse> userResponses = listUser.stream().map(user -> mapper.map(user, UserResponse.class)).collect(Collectors.toList());
        return PageResponse.create(totalPages, totalElements, userResponses);
    }

    public IdResponse createEmployees(CreateEmployeeRequest input) {
        Optional<User> optManager = userRepository.findByEmail(input.getEmail());
        if (optManager.isPresent()) {
            throw new DataNotFoundException("Email da ton tai");
        }
        long number = userRepository.countUser() + 1;
        User employee = userRepository.save((Employee) mapper.map(input, Employee.class)
                .setStaffCode("STAFF" + Numbers(number))
                .setStatus(AccStatusEnum.ACTIVATED))
                .setRole(new UserRole(2, "STAFF"));
        employee.setPassword(hashUtils.hash(employee.getPassword()));
        return new IdResponse(employee.getId());

    }

    public IdResponse updateEmployee(int id, UpdateEmployeeRequest input) {
        Optional<User> employee = userRepository.findById(id);
        if (employee.isEmpty()) {
            throw new DataNotFoundException("User Not Found");
        }
        User user = employee.get();
        user.setEmail(input.getEmail());
        user.setFullName(input.getFullName());
        user.setStatus(input.getStatus());
        userRepository.save(user);
        return new IdResponse(user.getId());
    }

    public IdResponse deleteEmployee(int id) {

        User user = userRepository.getById(id);
        if (user == null) {
            throw new DataNotFoundException("User Not Found");
        }
        userRepository.delete(user);
        return new IdResponse(user.getId());
    }
}
