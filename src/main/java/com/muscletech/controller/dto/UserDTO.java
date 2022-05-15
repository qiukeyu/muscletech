package com.muscletech.controller.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userPhoneNumber;
    private String userAvatar;
    private String token;
    private String role;
}
