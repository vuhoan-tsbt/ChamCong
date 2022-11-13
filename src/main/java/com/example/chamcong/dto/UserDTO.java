package com.example.chamcong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String dateOfBirth;
    private String department;
    private String position;
    private String avatar;
    private String address;
}
