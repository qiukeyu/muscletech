package com.example.demo.controller.dto;

import lombok.Data;

@Data
public class UserPasswordDTO {
    private String phoneNumber;
    private String password;
    private String newPassword;
}
