package com.example.chamcong.controller;

import com.example.chamcong.business.UserBusiness;
import com.example.chamcong.dto.*;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.ChainPasswordRequest;
import com.example.chamcong.model.request.UpdateInformationRequest;
import com.example.chamcong.model.response.ProfileUserResponse;
import com.example.chamcong.model.response.IdResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequestMapping(value = "/user/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController

public class UserController {

    private final UserBusiness userBusiness;

    public UserController(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @GetMapping("/profile")
    public RootResponse<ProfileUserResponse> profileEmployee()  {
       return RootResponse.success("", userBusiness.profileEmployee());
    }

    @PostMapping("/information")
    public RootResponse<IdResponse> staffUpdateInformation(@RequestBody @Valid UpdateInformationRequest input){
        return RootResponse.success("Cập nhật thông tin thành công", userBusiness.staffUpdateInformation(input));
    }

    @PostMapping("/chain_password")
    public RootResponse<IdResponse> chainPassword(@RequestBody @Valid ChainPasswordRequest input) {
        return RootResponse.success("Đổi Mật Khẩu Thành Công.", userBusiness.chainPassword(input));
    }

    @GetMapping("/department")
    public RootResponse<List<DepartmentDTO>> department(){
        return RootResponse.success("",userBusiness.department());
    }

    @GetMapping("/news")
    public RootResponse<List<NewsDTO>> news(){
        return RootResponse.success("",userBusiness.news());
    }

    @GetMapping("/list-employee")
    public RootResponse<List<UserDTO>> listStaff(){
        return RootResponse.success("",userBusiness.listStaff());
    }
    @GetMapping("/get-user/{id}")
    public RootResponse<UserDTOId> getUserId(@PathVariable("id") Integer id){
        return  RootResponse.success("",userBusiness.getUserId(id));
    }



}
