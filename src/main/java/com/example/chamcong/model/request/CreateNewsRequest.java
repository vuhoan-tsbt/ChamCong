package com.example.chamcong.model.request;

import com.example.chamcong.constant.Messages;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class CreateNewsRequest {

    @NotBlank(message = Messages.MES031)
    private String typeOfNews;

    @NotBlank(message = Messages.MES030)
    private String content;
}
