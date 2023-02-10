package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.TimeKeeping;
import com.example.chamcong.model.request.DotInformationRequest;

import java.util.List;

public interface CustomerTimeKeepingRepository {

    List<TimeKeeping> findByIdUser(Integer id);
    TimeKeeping getEntryTime(Integer userId);
    List<TimeKeeping> getTimeKeeping(Integer id);
    List<TimeKeeping> getByALlTimeKeeping(DotInformationRequest input);
    List<TimeKeeping> getTimeKeepingUser(String months, Integer userId);
    Integer getWorkingTime (Integer userId, String months);
    List<TimeKeeping> getKeepingUserDayNow(String staffCode);


}
