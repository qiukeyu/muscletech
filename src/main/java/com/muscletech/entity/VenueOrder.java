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
@ApiModel(value = "OrderModel", description = "")
@Data
public class VenueOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("venue order code")
    private Integer orderId;

    @ApiModelProperty("venue order date")
    private String orderDate;

    @ApiModelProperty("venue order time")
    private Integer orderTime;

    @ApiModelProperty("venue order user")
    private Integer userId;

    @ApiModelProperty("venue order venue")
    private Integer venueId;

    @ApiModelProperty("")
    private String venueName;

    @ApiModelProperty("need coach or not")
    private Integer coach;

    @ApiModelProperty("final price")
    private Integer price;
}
