package com.example.chamcong.business.staff;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.dto.TimeKeepingDetailsDTO;
import com.example.chamcong.entity.TimeKeeping;
import com.example.chamcong.entity.User;
import com.example.chamcong.model.response.TimeKeepingOutResponse;
import com.example.chamcong.model.response.TimeKeepingEntryResponse;
import com.example.chamcong.repository.TimeKeepingRepository;
import com.example.chamcong.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffTimeKeepingBusiness extends BaseBusiness {

    private final StringUtils stringUtils;

    private final TimeKeepingRepository timeKeepingRepository;


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
        int months = calendar.get(Calendar.MONTH) + 1;
        timeKeeping.setMonths(String.valueOf(months));
        timeKeeping.setEntryTime(LocalDateTime.now());
        timeKeeping.setWorkingTime(0D);
        timeKeepingRepository.save(timeKeeping);
        return new TimeKeepingEntryResponse(user.getId(), radixCode);
    }

    public TimeKeepingOutResponse timeOutKeeping() throws ParseException {
        User user = getCurrentUser();
        TimeKeeping timeKeeping = timeKeepingRepository.getEntryTime(user.getId());
        timeKeeping.setTimeout(LocalDateTime.now());
        Long working = timeKeeping.getTimeout().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                - timeKeeping.getEntryTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Double hours = Double.valueOf(working / 1000 / 3600 - 1);
        timeKeeping.setWorkingTime(hours);
        timeKeepingRepository.save(timeKeeping);
        return new TimeKeepingOutResponse(user.getId());
    }

    public Integer getTimeKeeping() {
        User user = getCurrentUser();
        List<TimeKeepingDetailsDTO> result = new ArrayList<>();
        for (TimeKeeping timeK :
                timeKeepingRepository.getTimeKeeping(user.getId())) {
            TimeKeepingDetailsDTO dto = new TimeKeepingDetailsDTO();
            dto.setEntryTime(String.valueOf(timeK.getEntryTime()));
            dto.setTimeout(String.valueOf(timeK.getTimeout()));
            result.add(dto);
        }
        ;
        return result.isEmpty() ? 0 : (result.size() == 1 ? 1 : 2);
    }
    public List<TimeKeepingDetailsDTO> keepingUser(String months, Integer userId) {
        String monthss = String.valueOf(LocalDateTime.now().getMonth());
        if(monthss != null){
            monthss = months;
        }
        List<TimeKeeping> list = timeKeepingRepository.getTimeKeepingUser(monthss,userId);
        List<TimeKeepingDetailsDTO> dtoList = list.stream().map(keeping -> {
            TimeKeepingDetailsDTO dto = new TimeKeepingDetailsDTO();
            dto.setEntryTime(String.valueOf(keeping.getEntryTime()));
            dto.setTimeout(String.valueOf(keeping.getTimeout()));
            return dto;
        }).collect(Collectors.toList());
        return  dtoList;
    }
}
