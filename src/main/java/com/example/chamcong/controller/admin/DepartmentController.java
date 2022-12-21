package com.example.chamcong.controller.admin;

import com.example.chamcong.business.admin.DepartmentBusiness;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.CreateDepartmentRequest;
import com.example.chamcong.model.request.DepartmentRequest;
import com.example.chamcong.model.response.DepartmentResponse;
import com.example.chamcong.model.response.IdDepartmentResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Root;
import javax.validation.Valid;

@RequestMapping(value = "/api/department", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class DepartmentController {

    private final DepartmentBusiness departmentBusiness;

    public DepartmentController(DepartmentBusiness departmentBusiness) {
        this.departmentBusiness = departmentBusiness;
    }

    @GetMapping("/list_department")
    public RootResponse<PageResponse<DepartmentResponse>> listDepartment(@ModelAttribute DepartmentRequest input){
        return RootResponse.success("Danh sách phòng ban công ty ", departmentBusiness.listDepartment(input));
    }

    @PostMapping("/create")
    public RootResponse<IdDepartmentResponse> createDepartment(@RequestBody @Valid CreateDepartmentRequest input){
        return RootResponse.success("Thêm phòng ban thành công", departmentBusiness.createDepartment(input));
    }
    @PutMapping("/update/{id}")
    public RootResponse<IdDepartmentResponse> updateDepartment(@PathVariable("id") int id, @RequestBody @Valid CreateDepartmentRequest input){
        return RootResponse.success("Sửa phòng ban thành công", departmentBusiness.updateDepartment(id,input));
    }
    @DeleteMapping("/delete/{id}")
    public RootResponse<IdDepartmentResponse> deleteDepartment(@PathVariable("id") int id){
       return RootResponse.success("Xóa phòng ban thành công", departmentBusiness.deleteDepartment(id));
    }

}
