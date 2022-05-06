package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@ApiModel(value = "StaffModel", description = "")
@Data
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("staff code")
    private Integer staffId;

    @ApiModelProperty("staff id number")
    private String staffNumber;

    @ApiModelProperty("staff first name")
    private String staffFirstName;

    @ApiModelProperty("staff last name")
    private String staffLastName;

    @ApiModelProperty("staff password")
    private String staffPassword;

    @ApiModelProperty("staff avatar")
    private String staffAvatar;

    @ApiModelProperty("manager Id code")
    private Integer managerId;

    @ApiModelProperty("center id number")
    private Integer centerId;

    @ApiModelProperty("staff role")
    private String role;
}
