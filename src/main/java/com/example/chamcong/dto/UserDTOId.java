package com.example.chamcong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class UserDTOId {

    private String fullName;
    private String dateOfBirth;
    private Integer department;
    private Integer position;
    private String avatar;
    private String address;
    private Integer role;
}
