package com.example.chamcong.business.staff;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.dto.TimeKeepingDetailsDTO;
import com.example.chamcong.entity.TimeKeeping;
import com.example.chamcong.entity.User;
import com.example.chamcong.model.response.TimeKeepingOutResponse;
import com.example.chamcong.model.response.TimeKeepingEntryResponse;
import com.example.chamcong.repository.TimeKeepingRepository;
import com.example.chamcong.utils.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Service
public class StaffTimeKeepingBusiness extends BaseBusiness {

    private final StringUtils stringUtils;

    private final TimeKeepingRepository timeKeepingRepository;


    public StaffTimeKeepingBusiness(StringUtils stringUtils, TimeKeepingRepository timeKeepingRepository) {
        this.stringUtils = stringUtils;
        this.timeKeepingRepository = timeKeepingRepository;
    }


    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public TimeKeepingEntryResponse timeKeepingEntry() {
        User user = getCurrentUser();
        String radixCode = stringUtils.randomString(10);
        TimeKeeping timeKeeping = new TimeKeeping();
        timeKeeping.setRadixCode(radixCode);
        timeKeeping.setUser(user);
        Calendar calendar = Calendar.getInstance();
        int months = calendar.get(Calendar.MONTH ) + 1;
        timeKeeping.setMonths(String.valueOf(months));
        timeKeeping.setEntryTime(LocalDateTime.now());
        timeKeepingRepository.save(timeKeeping);
        return new TimeKeepingEntryResponse(user.getId(), radixCode);
    }
    public TimeKeepingOutResponse timeOutKeeping() {
        User user = getCurrentUser();
        TimeKeeping timeKeeping = timeKeepingRepository.getEntryTime(user.getId());
        timeKeeping.setTimeout(LocalDateTime.now());
        timeKeepingRepository.save(timeKeeping);
        return new TimeKeepingOutResponse(user.getId());
    }

    public TimeKeepingDetailsDTO getTimeKeeping() {
        User user = getCurrentUser();
        TimeKeeping keeping = timeKeepingRepository.getTimeKeeping(user.getId());
        TimeKeepingDetailsDTO dto = new TimeKeepingDetailsDTO();
        dto.setEntryTime(String.valueOf(keeping.getEntryTime()));
        dto.setTimeout(String.valueOf(keeping.getTimeout()));
        return dto;
    }
}
