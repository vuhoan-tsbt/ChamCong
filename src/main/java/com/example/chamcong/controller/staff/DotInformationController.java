package com.example.chamcong.controller.staff;

import com.example.chamcong.business.staff.DotInformationBusiness;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.request.DotInformationRequest;
import com.example.chamcong.model.request.SalaryEmployeeRequest;
import com.example.chamcong.model.response.DotInformationResponse;
import com.example.chamcong.model.response.SalaryEmployeeResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/dot/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@PreAuthorize("hasAnyAuthority('EDITER')")
public class DotInformationController {

    private final DotInformationBusiness dotInformationBusiness;

    public DotInformationController(DotInformationBusiness dotInformationBusiness) {
        this.dotInformationBusiness = dotInformationBusiness;
    }

    @GetMapping("/dot_information")
    public RootResponse<DotInformationResponse> dotInformation(@ModelAttribute DotInformationRequest input) {

        return RootResponse.success("Danh sách", dotInformationBusiness.dotInformation(input));
    }


    @PostMapping("/salary")
    public RootResponse<SalaryEmployeeResponse> salary(@RequestBody @Valid SalaryEmployeeRequest input) {
        return RootResponse.success("Lương của nhân viên là ", dotInformationBusiness.salary(input));

    }

}
