package com.muscletech.controller.dto;

import lombok.Data;

@Data
public class DataDTO {

    /** 所有订单数量 */
    private Integer order;

    /** 所有的收入 */
    private Integer income;

    /** 所有用户人数 */
    private Integer user;

    /** 所有员工人数 */
    private Integer staff;

    /** 今天每个项目的订单数量 */
    private Integer badminton;
    private Integer tennis;
    private Integer tableTennis;
    private Integer gym;
    private Integer swimming;
    private Integer basketball;

}
