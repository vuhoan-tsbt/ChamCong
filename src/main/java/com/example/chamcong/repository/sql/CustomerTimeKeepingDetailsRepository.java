package com.example.chamcong.repository.sql;

import com.example.chamcong.entity.TimeKeepingDetails;
import com.example.chamcong.model.request.DotInformationRequest;

import java.util.List;

public interface CustomerTimeKeepingDetailsRepository {

    List<TimeKeepingDetails> getByALlTimeKeeping(DotInformationRequest input);
}
