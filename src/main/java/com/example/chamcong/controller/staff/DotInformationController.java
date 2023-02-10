package com.example.chamcong.controller.staff;

import com.example.chamcong.business.staff.DotInformationBusiness;
import com.example.chamcong.dto.KeepingStaffDayNowDTO;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.DotInformationRequest;
import com.example.chamcong.model.request.ListSalaryEmployeeRequest;
import com.example.chamcong.model.request.SalaryEmployeeRequest;
import com.example.chamcong.model.response.DotInformationResponse;
import com.example.chamcong.model.response.ListSalaryEmployeeResponse;
import com.example.chamcong.model.response.SalaryEmployeeResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/dot", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@PreAuthorize("hasAnyAuthority('EDITER')")
public class DotInformationController {

    private final DotInformationBusiness dotInformationBusiness;

    public DotInformationController(DotInformationBusiness dotInformationBusiness) {
        this.dotInformationBusiness = dotInformationBusiness;
    }

    @PostMapping("/dot_information")
    public RootResponse<DotInformationResponse> dotInformation(@RequestBody DotInformationRequest input) {
        return RootResponse.success("Danh sách", dotInformationBusiness.dotInformation(input));
    }

    @PostMapping("/salary")
    public RootResponse<SalaryEmployeeResponse> salary(@RequestBody @Valid SalaryEmployeeRequest input) {
        return RootResponse.success("Lương của nhân viên là ", dotInformationBusiness.salary(input));
    }

    @PostMapping("/list-salary")
    public RootResponse<PageResponse<ListSalaryEmployeeResponse>> listSalaryEmployee(
            @RequestBody @Valid ListSalaryEmployeeRequest input) {
        return RootResponse.success("Danh sách lương nhân viên", dotInformationBusiness.listSalaryEmployee(input));
    }
    @GetMapping("/keeping-day")
    public RootResponse<List<KeepingStaffDayNowDTO>> keepingUserDayNow(@RequestParam(required = false) String staffCode){
        return RootResponse.success("Danh sách chấm công hôm nay",dotInformationBusiness.keepingUserDayNow(staffCode));
    }


}
