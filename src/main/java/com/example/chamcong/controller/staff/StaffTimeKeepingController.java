package com.example.chamcong.controller.staff;

import com.example.chamcong.business.staff.StaffTimeKeepingBusiness;
import com.example.chamcong.dto.TimeKeepingDetailsDTO;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.response.TimeKeepingOutResponse;
import com.example.chamcong.model.response.TimeKeepingEntryResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

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
    public RootResponse<TimeKeepingOutResponse> timeOutKeeping() throws ParseException {
        return RootResponse.success("Tạm Biệt Bạn", staffTimeKeepingBusiness.timeOutKeeping());
    }
    @GetMapping("/get-keeping")
    public RootResponse<?> getTimeKeeping(){
        return RootResponse.success("Bảng chấm công ",staffTimeKeepingBusiness.getTimeKeeping());
    }
    @GetMapping("/keeping-user")
    public RootResponse<List<TimeKeepingDetailsDTO>> keepingUser(@RequestParam(required = false) String months, @RequestParam Integer userId){
        return RootResponse.success("Lisst chấm công",staffTimeKeepingBusiness.keepingUser(months, userId));
    }




}
