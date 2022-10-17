package com.example.chamcong.model.response;

import com.example.chamcong.dto.TimeKeepingDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class DotInformationResponse {

    private String name;

    private String staffCode;

    private List<TimeKeepingDTO> keeping;
}
