package com.example.chamcong.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private int id;

    private String fullName;

    private String email;

    private String staffCode;

    private String dateOfBirth;

    private String address;

    private String createdAt;

    private String department;

    private String position;
}
