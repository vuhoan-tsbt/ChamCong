package com.example.chamcong.controller.staff;

import com.example.chamcong.business.staff.StaffTimeKeepingBusiness;
import com.example.chamcong.dto.TimeKeepingDetailsDTO;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.response.TimeKeepingOutResponse;
import com.example.chamcong.model.response.TimeKeepingEntryResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/timekeeping", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @GetMapping("/get-keeping")
    public RootResponse<TimeKeepingDetailsDTO> getTimeKeeping(){
        return RootResponse.success("Bảng chấm công ",staffTimeKeepingBusiness.getTimeKeeping());
    }




}
