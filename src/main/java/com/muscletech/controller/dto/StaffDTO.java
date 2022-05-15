package com.muscletech.controller.dto;

import lombok.Data;

@Data
public class StaffDTO {
    private Integer staffId;
    private String staffNumber;
    private String staffFirstName;
    private String staffLastName;
    private String staffPassword;
    private String staffAvatar;
    private String token;
    private String role;
    private Integer managerId;
    private Integer centerId;
}
