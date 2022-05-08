package com.example.demo.controller.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer userId;
    private String username;
    private String userPassword;
    private String userPhoneNumber;
    private String verification;
    private String userAvatar;
    private String token;
    private String role;
}
