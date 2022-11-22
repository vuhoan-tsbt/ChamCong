package com.example.chamcong.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUserResponse {

    private String fullName;

    private String email;

    private String department;

    private String position;

    private String address;

    private String avatar;

    private String dateOfBirth;

    private String phone;

    private String staffCode;
}
