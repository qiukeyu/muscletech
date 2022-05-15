package com.muscletech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muscletech.common.Constants;
import com.muscletech.entity.Card;
import com.muscletech.exception.ServiceException;
import com.muscletech.mapper.CardMapper;
import com.muscletech.service.ICardService;
import com.muscletech.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements ICardService {

    @Resource
    CardMapper cardMapper;

    @Override
    public List<Card> findAll() {
        QueryWrapper<Card> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", Objects.requireNonNull(TokenUtils.getCurrentUser()).getUserId());
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
