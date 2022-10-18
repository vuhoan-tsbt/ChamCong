package com.example.chamcong.business.staff;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.dto.TimeKeepingDTO;
import com.example.chamcong.entity.TimeKeepingDetails;
import com.example.chamcong.model.request.DotInformationRequest;
import com.example.chamcong.model.response.DotInformationResponse;
import com.example.chamcong.repository.TimeKeepingDetailsRepository;
import com.example.chamcong.repository.TimeKeepingRepository;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DotInformationBusiness extends BaseBusiness {

    private final TimeKeepingRepository timeKeepingRepository;
    private final TimeKeepingDetailsRepository timeKeepingDetailsRepository;

    public DotInformationBusiness(TimeKeepingRepository timeKeepingRepository, TimeKeepingDetailsRepository timeKeepingDetailsRepository) {
        this.timeKeepingRepository = timeKeepingRepository;
        this.timeKeepingDetailsRepository = timeKeepingDetailsRepository;
    }

    public DotInformationResponse dotInformation(DotInformationRequest input) {
        List<TimeKeepingDetails> timeKeeping = timeKeepingDetailsRepository.getByALlTimeKeeping(input);
        DotInformationResponse response = new DotInformationResponse();
        List<TimeKeepingDTO> dtoList = new ArrayList<>();
        if (timeKeeping.size() > 0) {
            response.setName(timeKeeping.get(0).getTimeKeeping().getUser().getFullName());
            response.setStaffCode(timeKeeping.get(0).getTimeKeeping().getUser().getStaffCode());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_MONTH);
        Date d = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        for (TimeKeepingDetails details : timeKeeping) {
            long a = details.getEntryTime().format(formatter).compareTo(simpleDateFormat.format(d));
            if (a < 0) {
                details.getEntryTime();
                details.getTimeout();
                TimeKeepingDTO dto = new TimeKeepingDTO();
                dto.setDay(details.getEntryTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                dto.setEntryTime(details.getEntryTime().toString());
                dto.setTimeout(details.getTimeout().toString());
                dtoList.add(dto);
            }
        }
        response.setKeeping(dtoList);
        return response;
    }
}
