package com.example.chamcong.business.staff;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.entity.TimeKeeping;
import com.example.chamcong.entity.TimeKeepingDetails;
import com.example.chamcong.entity.User;
import com.example.chamcong.model.response.TimeKeepingOutResponse;
import com.example.chamcong.model.response.TimeKeepingResponse;
import com.example.chamcong.repository.TimeKeepingDetailsRepository;
import com.example.chamcong.repository.TimeKeepingRepository;
import com.example.chamcong.utils.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StaffTimeKeepingBusiness extends BaseBusiness {

    private final StringUtils stringUtils;

    private final TimeKeepingRepository timeKeepingRepository;

    private final TimeKeepingDetailsRepository timeKeepingDetailsRepository;

    public StaffTimeKeepingBusiness(StringUtils stringUtils, TimeKeepingRepository timeKeepingRepository, TimeKeepingDetailsRepository timeKeepingDetailsRepository) {
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
        TimeKeeping timeKeeping = timeKeepingRepository.save(new TimeKeeping().setRadixCode(radixCode)
                .setUser(user));
        timeKeepingDetailsRepository.save(new TimeKeepingDetails().setTimeKeeping(timeKeeping)
                .setEntryTime(String.valueOf(LocalDateTime.now())));
        return new TimeKeepingResponse(user.getId(), radixCode);
    }

    public TimeKeepingOutResponse timeOutKeeping() {
        User user = getCurrentUser();
        List<TimeKeeping> timeKeeping = timeKeepingRepository.getByUserId(user);
        for (TimeKeeping keeping : timeKeeping) {
            TimeKeepingDetails timeKeepingDetails = timeKeepingDetailsRepository.getById(keeping);
            timeKeepingDetails.setTimeout(String.valueOf(LocalDateTime.now()));
            timeKeepingDetailsRepository.save(timeKeepingDetails);
        }
        return new TimeKeepingOutResponse(user.getId());
    }
}
