package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.VenueOrder;

import java.util.List;

public interface IOrderService extends IService<VenueOrder> {

    List<VenueOrder> findAll(UserDTO userDTO);

    VenueOrder get(Integer id);

}
