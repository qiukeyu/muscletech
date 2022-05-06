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
@ApiModel(value = "CardModel", description = "")
@Data
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("card code")
    private Integer cardId;

    @ApiModelProperty("card number")
    private String cardNumber;

    @ApiModelProperty("related user real name")
    private String cardName;

    @ApiModelProperty("related user phone number")
    private String cardPhone;

    @ApiModelProperty("related user code")
    private Integer userId;
}
