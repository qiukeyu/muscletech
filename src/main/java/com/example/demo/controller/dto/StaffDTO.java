package com.example.demo.controller.dto;

import lombok.Data;

@Data
public class StaffDTO {
    private Integer id;
    private String number;
    private String firstName;
    private String lastName;
    private String password;
    private String token;
    private String role;
}
