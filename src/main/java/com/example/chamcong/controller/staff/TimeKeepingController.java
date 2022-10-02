package com.example.chamcong.controller.staff;

import com.example.chamcong.business.staff.TimeKeepingBusiness;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.response.TimeKeepingResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/timekeeping/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@PreAuthorize("hasAnyAuthority('STAFF')")
public class TimeKeepingController {
    private final TimeKeepingBusiness  timeKeepingBusiness;

    public TimeKeepingController(TimeKeepingBusiness  timeKeepingBusiness){
        this.timeKeepingBusiness = timeKeepingBusiness;
    }

    @PostMapping("/entry")
    public RootResponse<TimeKeepingResponse> timeKeepingEntry(){
        return RootResponse.success("Cảm ơn bạn",timeKeepingBusiness.timeKeepingEntry());

    }
    @PostMapping("/out")
    public RootResponse<TimeKeepingResponse> timeOutKeeping(){
        return RootResponse.success("Tạm Biệt Bạn",timeKeepingBusiness.timeOutKeeping());
    }



}
