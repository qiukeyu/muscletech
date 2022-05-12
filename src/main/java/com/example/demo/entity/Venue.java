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
@ApiModel(value = "VenueModel", description = "")
@Data
public class Venue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("venue code")
    private Integer venueId;

    @ApiModelProperty("venue name")
    private String venueName;

    @ApiModelProperty("venue type")
    private Integer venueType;

    @ApiModelProperty("center code")
    private Integer centerId;

    @ApiModelProperty("capacity")
    private Integer capacity;

    @ApiModelProperty("price")
    private Integer price;

    @ApiModelProperty("open(0) or not")
    private Integer open;

    @ApiModelProperty("coach name")
    private String coach;

    @ApiModelProperty("coach price")
    private Integer coachPrice;

}
