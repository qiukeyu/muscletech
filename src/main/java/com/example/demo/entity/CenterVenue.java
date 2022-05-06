package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@ApiModel(value = "CenterVenueModel", description = "")
@Data
public class CenterVenue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty("venue code")
    private Integer venueId;

    @Id
    @ApiModelProperty("center code")
    private Integer centerId;

    @ApiModelProperty("capacity")
    private Integer capacity;

    @ApiModelProperty("price")
    private Integer price;

    @ApiModelProperty("open or not")
    private Integer open;
}
