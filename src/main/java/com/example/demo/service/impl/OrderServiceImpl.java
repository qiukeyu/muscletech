package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.VenueOrder;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, VenueOrder> implements IOrderService {
}
