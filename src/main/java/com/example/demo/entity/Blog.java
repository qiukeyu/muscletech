package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

@Entity
@ApiModel(value = "BlogModel", description = "")
@Data
public class Blog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("blog code")
    private Integer blogId;

    @ApiModelProperty("blog title")
    private String blogTitle;

    @ApiModelProperty("blog content")
    private String blogContent;

    @ApiModelProperty("blog create date and time")
    private String blogDateTime;

    @ApiModelProperty("related user code")
    private Integer userId;

}

