package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.Card;

import java.util.List;

public interface ICardService extends IService<Card> {

    List<Card> findAll(UserDTO userDTO);

    Card get(Integer id);
}
