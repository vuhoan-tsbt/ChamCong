package com.example.chamcong.business.admin;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.entity.*;
import com.example.chamcong.enumtation.AccStatusEnum;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.request.CreateEmployeeRequest;
import com.example.chamcong.model.request.UpdateEmployeeRequest;
import com.example.chamcong.model.response.IdResponse;
import com.example.chamcong.repository.DepartmentRepository;
import com.example.chamcong.repository.PositionRepository;
import com.example.chamcong.repository.UserRoleRepository;
import com.example.chamcong.utils.HashUtils;
import org.dozer.Mapper;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.request.SearchUserRequest;
import com.example.chamcong.model.response.UserResponse;
import com.example.chamcong.repository.UserRepository;
import org.springframework.beans.BeanUtils;
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

    private final DepartmentRepository departmentRepository;

    private final PositionRepository positionRepository;

    private final UserRoleRepository userRoleRepository;

    public UserAdminBusiness(UserRepository userRepository, Mapper mapper, HashUtils hashUtils, DepartmentRepository departmentRepository,PositionRepository positionRepository,UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.hashUtils = hashUtils;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.userRoleRepository = userRoleRepository;
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
        List<UserResponse> userResponses = listUser.stream().map(user -> {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setEmail(user.getEmail());
            userResponse.setStaffCode(user.getStaffCode());
            userResponse.setDateOfBirth(user.getDateOfBirth());
            userResponse.setCreatedAt(String.valueOf(user.getCreatedAt()));
            userResponse.setAddress(user.getAddress());
            userResponse.setDepartment(user.getDepartment().getDepartment());
            userResponse.setPosition(user.getPosition().getPosition());
            return userResponse;
        }).collect(Collectors.toList());
        return PageResponse.create(totalPages, totalElements, userResponses);
    }

    public IdResponse createEmployees(CreateEmployeeRequest input) {
        Optional<User> optManager = userRepository.findByEmail(input.getEmail());
        if (optManager.isPresent()) {
            throw new DataNotFoundException("Email da ton tai");
        }
        long number = userRepository.countUser() + 1;
        Employee employee = new Employee();
        employee.setEmail(input.getEmail());
        UserRole userRole = userRoleRepository.getById(input.getRole());
        employee.setRole(userRole);
        Department department = departmentRepository.getById(input.getDepartmentId());
        employee.setDepartment(department);
        Position position = positionRepository.getById(input.getPositionId());
        employee.setPosition(position);
        employee.setStaffCode("STAFF" + Numbers(number));
        employee.setStatus(AccStatusEnum.ACTIVATED);
        employee.setPassword(hashUtils.hash(employee.getPassword()));
        userRepository.save(employee);
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
