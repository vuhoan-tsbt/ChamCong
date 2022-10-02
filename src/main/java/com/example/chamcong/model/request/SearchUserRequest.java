package com.example.chamcong.model.request;

import com.example.chamcong.constant.Messages;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class SearchUserRequest {
    @Pattern(regexp = "^(.+)@(\\S+)$", message = Messages.MES001)
    @Size(min = 12, max = 100, message = Messages.MES010)
    private String email;


    private String name;

    private Integer page = 1;

    private Integer size = 5 ;
}
