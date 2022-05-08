package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.Card;
import com.example.demo.entity.Staff;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.CardMapper;
import com.example.demo.service.ICardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements ICardService {

    @Resource
    CardMapper cardMapper;

    @Override
    public List<Card> findAll(UserDTO userDTO) {
        QueryWrapper<Card> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userDTO.getUserId());
        queryWrapper.orderByDesc("card_id");
        List<Card> cardList = cardMapper.selectList(queryWrapper);
        return cardList;
    }

    @Override
    public Card get(Integer id) {
        QueryWrapper<Card> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("card_id", id);
        Card one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.ERROR, "system error");
        }
        return one;
    }
}
