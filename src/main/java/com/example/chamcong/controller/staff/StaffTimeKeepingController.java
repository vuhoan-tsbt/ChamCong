package com.example.chamcong.controller.staff;

import com.example.chamcong.business.staff.StaffTimeKeepingBusiness;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.response.TimeKeepingOutResponse;
import com.example.chamcong.model.response.TimeKeepingEntryResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/timekeeping/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@PreAuthorize("hasAnyAuthority('STAFF','EDITER')")
public class StaffTimeKeepingController {
    private final StaffTimeKeepingBusiness staffTimeKeepingBusiness;

    public StaffTimeKeepingController(StaffTimeKeepingBusiness staffTimeKeepingBusiness){
        this.staffTimeKeepingBusiness = staffTimeKeepingBusiness;
    }

    @PostMapping("/entry")
    public RootResponse<TimeKeepingEntryResponse> timeKeepingEntry(){
        return RootResponse.success("Cảm ơn bạn", staffTimeKeepingBusiness.timeKeepingEntry());

    }
    @PostMapping("/out")
    public RootResponse<TimeKeepingOutResponse> timeOutKeeping(){
        return RootResponse.success("Tạm Biệt Bạn", staffTimeKeepingBusiness.timeOutKeeping());
    }



}
