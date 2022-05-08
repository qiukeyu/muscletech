package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@ApiModel(value = "UserModel", description = "")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "id-generator", strategy = "increment")
    @GeneratedValue(generator = "id-generator")
    @ApiModelProperty("user code")
    private Integer userId;

    @ApiModelProperty("user name")
    private String username;

    @ApiModelProperty("user phone number")
    private String userPhoneNumber;

    @ApiModelProperty("user password")
    private String userPassword;

    @ApiModelProperty("user avatar")
    private String userAvatar;

    @ApiModelProperty("user role")
    private String role;
}
