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
@ApiModel(value = "CommentModel", description = "")
@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("comment code")
    private Integer commentId;

    @ApiModelProperty("comment content")
    private String commentContent;

    @ApiModelProperty("comment crete date and time")
    private String commentDateTime;

    @ApiModelProperty("related user code")
    private Integer userId;

    @ApiModelProperty("related blog code")
    private Integer blogId;
}
