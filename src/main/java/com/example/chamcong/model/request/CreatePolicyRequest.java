package com.example.chamcong.model.request;

import com.example.chamcong.constant.Messages;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class CreatePolicyRequest {

    @NotBlank(message = Messages.MES029)
    private String namePolicy;
    @NotBlank(message = Messages.MES030)
    private String contentPolicy;
}
