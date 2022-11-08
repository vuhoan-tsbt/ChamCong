package com.example.chamcong.business.admin;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.entity.Department;
import com.example.chamcong.entity.User;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.request.CreateDepartmentRequest;
import com.example.chamcong.model.request.DepartmentRequest;
import com.example.chamcong.model.response.DepartmentResponse;
import com.example.chamcong.model.response.IdDepartmentResponse;
import com.example.chamcong.repository.DepartmentRepository;
import com.example.chamcong.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentBusiness extends BaseBusiness {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public DepartmentBusiness(DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;

        this.userRepository = userRepository;
    }

    public PageResponse<DepartmentResponse> listDepartment(DepartmentRequest input) {
        List<Department> departments = departmentRepository.findAllDepartment(input);
        int totalElements = departmentRepository.getAllDepartment(input);
        int totalPages = Math.max(1, (int) Math.ceil((double) totalElements / input.getSize()));
        List<DepartmentResponse> responses = departments.stream().map( department -> {
            DepartmentResponse departmentResponse = new DepartmentResponse();
            departmentResponse.setId(department.getId());
            departmentResponse.setDepartment(department.getDepartment());
            return departmentResponse;
        }).collect(Collectors.toList());
        return PageResponse.create(totalElements,totalPages,responses);
    }

    public IdDepartmentResponse createDepartment(CreateDepartmentRequest input) {
        Optional<Department> optDepartment = departmentRepository.getByDepartment(input.getDepartment());
        if (optDepartment.isPresent()){
            throw new DataNotFoundException("Phòng ban này đã tồn tại");
        }
        Department department = new Department();
        department.setDepartment(input.getDepartment());
        departmentRepository.save(department);
        return new IdDepartmentResponse(department.getId());
    }

    public IdDepartmentResponse updateDepartment(int id, CreateDepartmentRequest input) {
        Department department = departmentRepository.getById(id);
        if(department == null){
            throw  new DataNotFoundException("Phòng ban này không tồn tại");
        }
        department.setDepartment(input.getDepartment());
        departmentRepository.save(department);
        return new IdDepartmentResponse(department.getId());
    }

    public IdDepartmentResponse deleteDepartment(int id) {
        Department department = departmentRepository.getById(id);
        if(department == null){
            throw  new DataNotFoundException("Phòng ban này không tồn tại");
        }
        List<User> user = userRepository.getAllUserDepartment(id);
        if (user.size() > 0){
            throw new DataNotFoundException("Cần loại bỏ các nhân viên đang thuộc phòng ban này trước");
        }
        departmentRepository.delete(department);
        return new IdDepartmentResponse(department.getId());
    }
}
