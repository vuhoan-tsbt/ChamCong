package com.example.chamcong.controller.admin;

import com.example.chamcong.business.admin.UserAdminBusiness;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.CreateEmployeeRequest;
import com.example.chamcong.model.request.SearchUserRequest;
import com.example.chamcong.model.request.UpdateEmployeeRequest;
import com.example.chamcong.model.response.IdResponse;
import com.example.chamcong.model.response.UserResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserAdminController {
    private final UserAdminBusiness userAdminBusiness;


    public UserAdminController(UserAdminBusiness userAdminBusiness) {
        this.userAdminBusiness = userAdminBusiness;

    }
    @GetMapping("/list-user")
    public RootResponse<PageResponse<UserResponse>> listUser(@ModelAttribute @Valid SearchUserRequest input) {
        return RootResponse.success("Get list user success", userAdminBusiness.listUser(input));
    }

    @PostMapping("/create-employee")
    public RootResponse<IdResponse> createEmployees(@RequestBody @Valid CreateEmployeeRequest input) {
        return RootResponse.success("create employees success", userAdminBusiness.createEmployees(input));
    }
    @PostMapping("/user/{id}")
    public RootResponse<IdResponse> updateEmployee(@PathVariable("id") int id, @RequestBody @Valid UpdateEmployeeRequest input) {
        return RootResponse.success("update employee success", userAdminBusiness.updateEmployee(id, input));
    }
    @DeleteMapping("/user/{id}")
    public RootResponse<IdResponse> deleteEmployee(@PathVariable("id") int id) {
        return RootResponse.success("delete employee success", userAdminBusiness.deleteEmployee(id));
    }



}
