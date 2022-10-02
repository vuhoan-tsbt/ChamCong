package com.example.chamcong.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class TimeKeepingResponse {

    private int id;

    private String radixCode;

}
