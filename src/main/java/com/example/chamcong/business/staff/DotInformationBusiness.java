package com.example.chamcong.business.staff;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.dto.TimeKeepingDTO;
import com.example.chamcong.entity.Position;
import com.example.chamcong.entity.Salary;
import com.example.chamcong.entity.TimeKeepingDetails;
import com.example.chamcong.entity.User;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.request.DotInformationRequest;
import com.example.chamcong.model.request.SalaryEmployeeRequest;
import com.example.chamcong.model.response.DotInformationResponse;
import com.example.chamcong.model.response.SalaryEmployeeResponse;
import com.example.chamcong.repository.*;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DotInformationBusiness extends BaseBusiness {

    private final TimeKeepingRepository timeKeepingRepository;
    private final TimeKeepingDetailsRepository timeKeepingDetailsRepository;
    private final UserRepository userRepository;
    private final PositionRepository positionRepository;
    private final SalaryRepository salaryRepository;

    public DotInformationBusiness(TimeKeepingRepository timeKeepingRepository, TimeKeepingDetailsRepository timeKeepingDetailsRepository, UserRepository userRepository, PositionRepository positionRepository, SalaryRepository salaryRepository) {
        this.timeKeepingRepository = timeKeepingRepository;
        this.timeKeepingDetailsRepository = timeKeepingDetailsRepository;
        this.userRepository = userRepository;
        this.positionRepository = positionRepository;
        this.salaryRepository = salaryRepository;
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

    public SalaryEmployeeResponse salary(SalaryEmployeeRequest input) {
        User user = userRepository.getSalary(input.getStaffCode());
        if (user == null) {
            throw new DataNotFoundException("Nhân viên không tồn tại");
        }
        user.getPosition().getSalary();
        user.getPosition().getWage();
        long salaryHourly = 0;
        long actualSalary = 0;
        salaryHourly = (user.getPosition().getSalary()) / (input.getTotalNumberOfWorkingDaysInTheMonth()) / (input.getNumberOfHoursWorkedInADay());
        actualSalary = salaryHourly * input.getTotalWorkingHours() + user.getPosition().getWage();

        Salary salary = new Salary();
        salary.setSalaryForOneHourWork(salaryHourly);
        salary.setTotalWorkingHours(input.getTotalWorkingHours());
        salary.setTotalSalary(actualSalary);
        salary.setUser(user);
        salaryRepository.save(salary);

        SalaryEmployeeResponse salaryEmployeeResponse = new SalaryEmployeeResponse();
        salaryEmployeeResponse.setName(user.getFullName());
        salaryEmployeeResponse.setStaffCode(input.getStaffCode());
        salaryEmployeeResponse.setActualGrossSalary(actualSalary);
        return salaryEmployeeResponse;
    }
}
