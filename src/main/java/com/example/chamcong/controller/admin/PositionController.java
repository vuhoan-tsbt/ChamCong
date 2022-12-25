package com.example.chamcong.controller.admin;

import com.example.chamcong.business.admin.PositionBusiness;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.CreatePositionRequest;
import com.example.chamcong.model.request.PositionRequest;
import com.example.chamcong.model.response.IdPositionResponse;
import com.example.chamcong.model.response.PositionResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/api/position", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class PositionController {

    private final PositionBusiness positionBusiness;

    public PositionController(PositionBusiness positionBusiness) {
        this.positionBusiness = positionBusiness;
    }

    @PostMapping("/list-position")
    public RootResponse<PageResponse<PositionResponse>> listPosition(@RequestBody PositionRequest input) {
        return RootResponse.success("Danh sách các chức vụ ", positionBusiness.listPosition(input));
    }

    @GetMapping("/list-position")
    public RootResponse<PageResponse<PositionResponse>> listPosition1(@ModelAttribute PositionRequest input) {
        return RootResponse.success("Danh sách các chức vụ ", positionBusiness.listPosition(input));
    }

    @PostMapping("/create-position")
    public RootResponse<IdPositionResponse> createPosition(@RequestBody @Valid CreatePositionRequest input) {
        return RootResponse.success("Thêm chức vụ thành công", positionBusiness.createPosition(input));
    }

    @PutMapping("/update-position/{id}")
    public RootResponse<IdPositionResponse> updatePosition(@PathVariable("id") int id,
            @RequestBody @Valid CreatePositionRequest input) {
        return RootResponse.success("Sửa chức vụ thành công", positionBusiness.updatePosition(id, input));
    }

    @DeleteMapping("/delete-position/{id}")
    public RootResponse<IdPositionResponse> deletePosition(@PathVariable("id") int id) {
        return RootResponse.success("Xóa chức vụ thành công", positionBusiness.deletePosition(id));
    }
}
