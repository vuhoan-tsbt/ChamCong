package com.example.chamcong.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PositionResponse {

    private int id;

    private Long allowance;

    private String position;

    private Long salary;

    private Integer wage;
}
