package com.example.chamcong.business.staff;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.entity.TimeKeeping;
import com.example.chamcong.entity.TimeKeepingDetails;
import com.example.chamcong.entity.User;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.response.TimeKeepingResponse;
import com.example.chamcong.repository.TimeKeepingDetailsRepository;
import com.example.chamcong.repository.TimeKeepingRepository;
import com.example.chamcong.utils.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeKeepingBusiness extends BaseBusiness {

    private final StringUtils stringUtils;

    private final TimeKeepingRepository timeKeepingRepository;

    private final TimeKeepingDetailsRepository timeKeepingDetailsRepository;

    public TimeKeepingBusiness(StringUtils stringUtils, TimeKeepingRepository timeKeepingRepository, TimeKeepingDetailsRepository timeKeepingDetailsRepository) {
        this.stringUtils = stringUtils;
        this.timeKeepingRepository = timeKeepingRepository;
        this.timeKeepingDetailsRepository = timeKeepingDetailsRepository;
    }


    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public TimeKeepingResponse timeKeepingEntry() {
        User user = getCurrentUser();
        String radixCode = stringUtils.randomString(10);
        timeKeepingRepository.save(new TimeKeeping().setRadixCode(radixCode)
                .setUser(user));
        timeKeepingDetailsRepository.save(new TimeKeepingDetails().setUser(user)
                .setEntryTime(String.valueOf(LocalDateTime.now())));
        return new TimeKeepingResponse(user.getId(), radixCode);
    }

    public TimeKeepingResponse timeOutKeeping() {
        User user = getCurrentUser();
        String radixCode = stringUtils.randomString(10);
        timeKeepingRepository.save(new TimeKeeping().setRadixCode(radixCode)
                .setUser(user));
        timeKeepingDetailsRepository.save(new TimeKeepingDetails().setUser(user)
                .setTimeout(String.valueOf(LocalDateTime.now())));
        return new TimeKeepingResponse(user.getId(), radixCode);
    }
}
