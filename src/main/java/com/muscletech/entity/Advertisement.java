package com.muscletech.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@ApiModel(value = "AdvertisementModel", description = "")
@Data
public class Advertisement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("advertisement code")
    private Integer advertisementId;

    @ApiModelProperty(value = "advertisement name")
    private String advertisementName;

    @ApiModelProperty("advertisement picture url")
    private String advertisementPicture;

    @ApiModelProperty("created date time")
    private String datetime;

    @ApiModelProperty("center")
    private Integer centerId;
}
