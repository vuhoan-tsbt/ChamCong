package com.example.chamcong.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PolicyResponse {

    private Integer id;

    private String namePolicy;

    private String contentPolicy;


}
