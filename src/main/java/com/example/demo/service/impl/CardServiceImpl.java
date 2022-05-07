package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Card;
import com.example.demo.mapper.CardMapper;
import com.example.demo.service.ICardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements ICardService {
}
