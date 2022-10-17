package com.example.chamcong.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DotInformationRequest {

    private String month;

    private String staffCode;
}
