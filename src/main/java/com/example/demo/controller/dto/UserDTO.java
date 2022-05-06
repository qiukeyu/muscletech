package com.example.demo.controller.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String phoneNumber;
    private String verification;
    private String avatar;
    private String token;
    private String role;
}
