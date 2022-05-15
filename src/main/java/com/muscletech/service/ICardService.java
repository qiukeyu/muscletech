package com.muscletech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muscletech.entity.Card;

import java.util.List;

public interface ICardService extends IService<Card> {

    List<Card> findAll();

    Card get(Integer id);
}
